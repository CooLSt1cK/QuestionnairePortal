package com.aleksieienko.questionnaire.portal.web.bean;

import com.aleksieienko.questionnaire.portal.db.entity.Field;
import com.aleksieienko.questionnaire.portal.db.entity.FieldName;
import com.aleksieienko.questionnaire.portal.db.entity.Questionnaire;
import com.aleksieienko.questionnaire.portal.db.entity.Response;
import com.aleksieienko.questionnaire.portal.service.FieldNameService;
import com.aleksieienko.questionnaire.portal.service.FieldService;
import com.aleksieienko.questionnaire.portal.service.QuestionnaireService;
import com.aleksieienko.questionnaire.portal.service.ResponseService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class QuestionnaireBean {
    private List<Field> fields;
    private String[] values;

    @ManagedProperty(value = "#{fieldServiceImpl}")
    private FieldService fieldService;
    @ManagedProperty(value = "#{fieldNameServiceImpl}")
    private FieldNameService fieldNameService;
    @ManagedProperty(value = "#{responseServiceImpl}")
    private ResponseService responseService;
    @ManagedProperty(value = "#{questionnaireServiceImpl}")
    private QuestionnaireService questionnaireService;
    @ManagedProperty(value = "#{authorizeChecker}")
    private AuthorizeChecker authorizeChecker;

    public void setAuthorizeChecker(AuthorizeChecker authorizeChecker) {
        this.authorizeChecker = authorizeChecker;
    }

    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    public void setResponseService(ResponseService responseService) {
        this.responseService = responseService;
    }

    public void setFieldNameService(FieldNameService fieldNameService) {
        this.fieldNameService = fieldNameService;
    }

    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public List<String> splitOption(Field field){
        String option = field.getOption();
        if(option==null || option.isEmpty()){
            return null;
        }
        List<String> list= new ArrayList<String>(Arrays.asList(option.split("\\r?\\n")));
        return list;
    }

    public String submit(){
        List<Questionnaire> list = new ArrayList<>();
        Response response = new Response();
        for(int i=0;i<fields.size();i++){
            if(values[i]==null || values[i].isEmpty()){
                continue;
            }/**/
            FieldName fieldName = fieldNameService.getByName(fields.get(i).getLabel());
            list.add(new Questionnaire(null,values[i],fieldName));
        }
        response.setQuestionnaires(list);
        responseService.add(response);
        if(response.getId()==null){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Wrong data", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "questionnaire";
        }
        return "success?faces-redirect=true";
    }

    @PostConstruct
    public void init(){
        authorizeChecker.checkAccess(true);
        fields = fieldService.getActive();
        values = new String[fields.size()];
    }
}
