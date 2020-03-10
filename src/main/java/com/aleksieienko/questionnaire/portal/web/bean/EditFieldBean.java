package com.aleksieienko.questionnaire.portal.web.bean;

import com.aleksieienko.questionnaire.portal.db.entity.Field;
import com.aleksieienko.questionnaire.portal.db.entity.Type;
import com.aleksieienko.questionnaire.portal.service.FieldService;
import com.aleksieienko.questionnaire.portal.service.TypeService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
@RequestScoped
public class EditFieldBean {
    @ManagedProperty(value = "#{param['id']}")
    private Integer id;
    private String label;
    private Type type;
    private List<Type> types;
    private String option;
    private Boolean visible;
    private Boolean required;
    private Boolean active;

    @ManagedProperty(value = "#{fieldServiceImpl}")
    private FieldService fieldService;
    @ManagedProperty(value = "#{typeServiceImpl}")
    private TypeService typeService;
    @ManagedProperty(value = "#{authorizeChecker}")
    private AuthorizeChecker authorizeChecker;

    public void setAuthorizeChecker(AuthorizeChecker authorizeChecker) {
        this.authorizeChecker = authorizeChecker;
    }

    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public void typeChanged(ValueChangeEvent e) {
        type = (Type) e.getNewValue();
        hideOption();
        FacesContext.getCurrentInstance().renderResponse();
    }

    public void hideOption(){
        visible = type != null
                && (type.getName().equals("radio button")
                || type.getName().equals("combobox"));
    }

    @PostConstruct
    public void init() {
        authorizeChecker.checkAccess(false);
        Field field = fieldService.getById(id);
        label = field.getLabel();
        type = field.getType();
        option = field.getOption();
        required = field.getRequired();
        active = field.getActive();
        types = typeService.getAll();
        hideOption();
    }

    public String save(){
        Field field= new Field(id,label,type.getId(),option,required,active,type);
        fieldService.add(field);
        if(field.getId() == null){
            return "editField?id=id";
        }
        return "fields?faces-redirect=true";
    }
}
