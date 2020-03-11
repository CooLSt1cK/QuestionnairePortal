package com.aleksieienko.questionnaire.portal.web.bean;

import com.aleksieienko.questionnaire.portal.db.entity.Field;
import com.aleksieienko.questionnaire.portal.db.entity.FieldName;
import com.aleksieienko.questionnaire.portal.db.entity.Type;
import com.aleksieienko.questionnaire.portal.service.FieldNameService;
import com.aleksieienko.questionnaire.portal.service.FieldService;
import com.aleksieienko.questionnaire.portal.service.TypeService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
@RequestScoped
public class AddFieldBean {
    private String label;
    private Type type;
    private List<Type> types;
    private String option;
    private Boolean required;
    private Boolean active;

    @ManagedProperty(value = "#{fieldServiceImpl}")
    private FieldService fieldService;
    @ManagedProperty(value = "#{fieldNameServiceImpl}")
    private FieldNameService fieldNameService;
    @ManagedProperty(value = "#{typeServiceImpl}")
    private TypeService typeService;

    public void setFieldNameService(FieldNameService fieldNameService) {
        this.fieldNameService = fieldNameService;
    }

    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void typeChanged(ValueChangeEvent e) {
        type = (Type) e.getNewValue();
    }

    @PostConstruct
    public void init() {
        types = typeService.getAll();
    }

    public String add(){
        if(!(type.getName().equals("radio button") || type.getName().equals("combobox"))) {
            option = null;
        }
        Field field = new Field(null, label, type.getId(), option, required, active, type);
        fieldService.add(field);
        if(field.getId() == null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Wrong data", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "addField";
        }
        fieldNameService.add(new FieldName(null,field.getLabel()));
        return "fields?faces-redirect=true";
    }
}
