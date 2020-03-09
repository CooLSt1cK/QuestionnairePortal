package com.aleksieienko.questionnaire.portal.web.bean;

import com.aleksieienko.questionnaire.portal.db.entity.VerificationToken;
import com.aleksieienko.questionnaire.portal.service.VerificationTokenService;
import com.aleksieienko.questionnaire.portal.web.AttributeNames;
import com.aleksieienko.questionnaire.portal.web.FacesContextFactory;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

@ManagedBean
@RequestScoped
public class ConfirmVerificationBean {
    @ManagedProperty(value = "#{param['token']}")
    private String token;
    @ManagedProperty(value = "#{verificationTokenServiceImpl}")
    private VerificationTokenService verificationTokenService;
    @ManagedProperty(value = "#{authorizeChecker}")
    private AuthorizeChecker authorizeChecker;

    public void setAuthorizeChecker(AuthorizeChecker authorizeChecker) {
        this.authorizeChecker = authorizeChecker;
    }

    public void setVerificationTokenService(VerificationTokenService verificationTokenService) {
        this.verificationTokenService = verificationTokenService;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void navigate(PhaseEvent event){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        VerificationToken verificationToken = verificationTokenService.getTokenByToken(token);
        Calendar calendar = Calendar.getInstance();
        if(verificationToken == null || (verificationToken.getExpiryDate().getTime()-calendar.getTime().getTime())<=0) {
            FacesContextFactory.getRequest().setAttribute(AttributeNames.SUCCESS_BODY,"Access denied!");
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "success");
            return;
        }
        verificationTokenService.remove(verificationToken);
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "success");
    }

}
