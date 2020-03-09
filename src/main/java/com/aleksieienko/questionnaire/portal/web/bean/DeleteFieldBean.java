package com.aleksieienko.questionnaire.portal.web.bean;

import com.aleksieienko.questionnaire.portal.db.entity.Field;
import com.aleksieienko.questionnaire.portal.service.FieldService;
import com.aleksieienko.questionnaire.portal.web.AttributeNames;
import com.aleksieienko.questionnaire.portal.web.FacesContextFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

@ManagedBean
@RequestScoped
public class DeleteFieldBean {
    @ManagedProperty(value = "#{param['id']}")
    private Integer id;
    @ManagedProperty(value = "#{authorizeChecker}")
    private AuthorizeChecker authorizeChecker;

    public void setAuthorizeChecker(AuthorizeChecker authorizeChecker) {
        this.authorizeChecker = authorizeChecker;
    }

    @ManagedProperty(value = "#{fieldServiceImpl}")
    private FieldService fieldService;

    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void delete(PhaseEvent event){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Field field = fieldService.getById(id);
        if(field != null && fieldService.remove(field)){
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "success");
        }else {
            FacesContextFactory.getRequest().setAttribute(AttributeNames.SUCCESS_BODY, "Access denied!");
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "failure");
        }
    }
}
