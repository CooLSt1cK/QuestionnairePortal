package com.aleksieienko.questionnaire.portal.web.bean;

import com.aleksieienko.questionnaire.portal.db.entity.User;
import com.aleksieienko.questionnaire.portal.web.AttributeNames;
import com.aleksieienko.questionnaire.portal.web.BeanUtil;
import com.aleksieienko.questionnaire.portal.web.FacesContextFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class logOutBean  {
    public String logOut(){
        User user = (User) FacesContextFactory.getRequest().getSession().getAttribute(AttributeNames.USER);
        FacesContextFactory.getRequest().getSession().setAttribute(AttributeNames.USER,null);
        BeanUtil.setCookie(user,-1);
        return "logIn";
    }
}
