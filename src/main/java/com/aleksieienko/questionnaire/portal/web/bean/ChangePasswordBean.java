package com.aleksieienko.questionnaire.portal.web.bean;

import com.aleksieienko.questionnaire.portal.db.entity.User;
import com.aleksieienko.questionnaire.portal.service.UserService;
import com.aleksieienko.questionnaire.portal.web.AttributeNames;
import com.aleksieienko.questionnaire.portal.web.FacesContextFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class ChangePasswordBean {
    private String newPassword;
    private String oldPassword;

    @ManagedProperty(value = "#{userServiceImpl}")
    private UserService userService;
    @ManagedProperty(value = "#{authorizeChecker}")
    private AuthorizeChecker authorizeChecker;

    public void setAuthorizeChecker(AuthorizeChecker authorizeChecker) {
        this.authorizeChecker = authorizeChecker;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String change(){
        String result;
        HttpSession session = FacesContextFactory.getRequest().getSession();
        User user = (User) session.getAttribute(AttributeNames.USER);
        if(userService.changePassword(user,newPassword,oldPassword)){
            FacesContextFactory.getRequest().setAttribute(AttributeNames.SUCCESS_BODY, "Success");
            result = "success";
        }else{
            result="changePassword";
        }
        return result;
    }
}
