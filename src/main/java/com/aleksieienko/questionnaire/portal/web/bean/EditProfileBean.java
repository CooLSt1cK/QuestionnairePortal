package com.aleksieienko.questionnaire.portal.web.bean;

import com.aleksieienko.questionnaire.portal.db.entity.User;
import com.aleksieienko.questionnaire.portal.service.UserService;
import com.aleksieienko.questionnaire.portal.service.VerificationTokenService;
import com.aleksieienko.questionnaire.portal.web.AttributeNames;
import com.aleksieienko.questionnaire.portal.web.FacesContextFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class EditProfileBean {
    @ManagedProperty(value = "#{user.email}")
    private String email;
    @ManagedProperty(value = "#{user.firstName}")
    private String firstName;
    @ManagedProperty(value = "#{user.lastName}")
    private String lastName;
    @ManagedProperty(value = "#{user.phone}")
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

    public String save(){
        String result = "editProfile";
        User user = (User) FacesContextFactory.getRequest().getSession().getAttribute(AttributeNames.USER);
        User newUser = new User(user.getId(),email,user.getHash(),user.getSalt(),firstName,lastName,phone);
        if(userService.updateUser(newUser)){
            result = "success";
        }
        return result;
    }
}
