package com.aleksieienko.questionnaire.portal.web.converter;

import com.aleksieienko.questionnaire.portal.db.entity.Type;
import com.aleksieienko.questionnaire.portal.service.TypeService;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
@ApplicationScoped
public class TypeConverter implements Converter {
    @ManagedProperty(value = "#{typeServiceImpl}")
    private TypeService typeService;

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        try
        {
            return typeService.getByName(s);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o instanceof Type)
        {
            Type type = (Type) o;
            return type.getName();
        }
        else
        {
            StringBuilder sbError = new StringBuilder("The object of class ");
            sbError.append(o.getClass().getName()).append(" is not of Type");
            throw new ClassCastException(sbError.toString());
        }
    }
}
