package com.aleksieienko.questionnaire.portal.web.bean;

import com.aleksieienko.questionnaire.portal.db.entity.User;
import com.aleksieienko.questionnaire.portal.db.entity.UserUtil;
import com.aleksieienko.questionnaire.portal.service.UserService;
import com.aleksieienko.questionnaire.portal.service.VerificationTokenService;
import com.aleksieienko.questionnaire.portal.web.AttributeNames;
import com.aleksieienko.questionnaire.portal.web.BeanUtil;
import com.aleksieienko.questionnaire.portal.web.FacesContextFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class LogInBean {
    private String message;
    private String email;
    private String password;
    private boolean rememberMe;

    @ManagedProperty(value = "#{userServiceImpl}")
    private UserService userService;
    @ManagedProperty(value = "#{verificationTokenServiceImpl}")
    private VerificationTokenService verificationTokenService;
    @ManagedProperty(value = "#{authorizeChecker}")
    private AuthorizeChecker authorizeChecker;

    public void setAuthorizeChecker(AuthorizeChecker authorizeChecker) {
        this.authorizeChecker = authorizeChecker;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setVerificationTokenService(VerificationTokenService verificationTokenService) {
        this.verificationTokenService = verificationTokenService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRememberMe(){
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe){
        this.rememberMe = rememberMe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void init(){
        authorizeChecker.checkAccess(true);
    }

    public String logIn(){
         String result = "login";
         User user = userService.getByEmail(email);
         try {
             if(user!=null && UserUtil.getHashedPassword(password,user.getSalt()).equals(user.getHash())
                && verificationTokenService.getTokenById(user.getId())==null) {
                 int expiry = (rememberMe)?(31_536_000):(-1);
                 BeanUtil.setCookie(user,expiry);
                 FacesContextFactory.getRequest().getSession().setAttribute(AttributeNames.USER,user);
                 result = "fields?faces-redirect=true";
                 System.out.println(result);
             }else {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong data!"));
             }
         } catch (InvalidKeySpecException e) {
             e.printStackTrace();
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         }
         return result;
     }
}
