package com.aleksieienko.questionnaire.portal.web.bean;

import com.aleksieienko.questionnaire.portal.db.entity.User;
import com.aleksieienko.questionnaire.portal.service.UserService;
import com.aleksieienko.questionnaire.portal.web.AttributeNames;
import com.aleksieienko.questionnaire.portal.web.BeanUtil;
import com.aleksieienko.questionnaire.portal.web.FacesContextFactory;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

@ManagedBean
@SessionScoped
public class AuthorizeChecker {
    @ManagedProperty(value = "#{userServiceImpl}")
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void check(){
        User user = (User) FacesContextFactory.getRequest().getSession().getAttribute(AttributeNames.USER);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(user == null){
            Cookie cookie;
            if((cookie = BeanUtil.getCookie("email")) != null){
                user = userService.getByEmail(cookie.getValue());
                FacesContextFactory.getRequest().getSession().setAttribute(AttributeNames.USER,user);
                facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "logged");
            }
        }else{
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "logged");
        }
    }
}
