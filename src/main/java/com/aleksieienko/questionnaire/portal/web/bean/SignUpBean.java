package com.aleksieienko.questionnaire.portal.web.bean;

import com.aleksieienko.questionnaire.portal.db.entity.User;
import com.aleksieienko.questionnaire.portal.service.UserService;
import com.aleksieienko.questionnaire.portal.service.VerificationTokenService;
import com.aleksieienko.questionnaire.portal.web.AttributeNames;
import com.aleksieienko.questionnaire.portal.web.BeanUtil;
import com.aleksieienko.questionnaire.portal.web.FacesContextFactory;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class SignUpBean {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @PostConstruct
    public void init(){
        authorizeChecker.checkAccess(true);
    }

    public String signUp(){
        User user = userService.getByEmail(email);
        if(user!=null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "User with this email already exist", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "signUp";
        }
        user = new User(null,email,null,null
                ,firstName.equals("") ? null : firstName
                ,lastName.equals("") ? null : lastName
                ,phone.equals("") ? null : phone);
        userService.add(user, password);
        System.out.println(user);
        if(user.getId()==null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Wrong data!", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "signUp";
        }
        String token = UUID.randomUUID().toString();
        verificationTokenService.createVerificationToken(user,token);
        BeanUtil.sendMail(user.getEmail(),token);
        FacesContextFactory.getRequest().setAttribute(AttributeNames.SUCCESS_BODY, "We send message to your email to confirm it.");
        return "success?faces-redirect=true";
    }
}
