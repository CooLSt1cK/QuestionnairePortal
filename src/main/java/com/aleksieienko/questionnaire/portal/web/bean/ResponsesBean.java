package com.aleksieienko.questionnaire.portal.web.bean;

import com.aleksieienko.questionnaire.portal.db.entity.FieldName;
import com.aleksieienko.questionnaire.portal.db.entity.Response;
import com.aleksieienko.questionnaire.portal.service.FieldNameService;
import com.aleksieienko.questionnaire.portal.service.ResponseService;
import com.aleksieienko.questionnaire.portal.web.BeanUtil;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ResponsesBean {
    private List<Response> responses;
    private List<FieldName> fieldNames;

    @ManagedProperty(value = "#{responseServiceImpl}")
    private ResponseService responseService;
    @ManagedProperty(value = "#{fieldNameServiceImpl}")
    private FieldNameService fieldNameService;
    @ManagedProperty(value = "#{authorizeChecker}")
    private AuthorizeChecker authorizeChecker;

    public void setAuthorizeChecker(AuthorizeChecker authorizeChecker) {
        this.authorizeChecker = authorizeChecker;
    }

    public void setResponseService(ResponseService responseService) {
        this.responseService = responseService;
    }

    public void setFieldNameService(FieldNameService fieldNameService) {
        this.fieldNameService = fieldNameService;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public List<FieldName> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<FieldName> fieldNames) {
        this.fieldNames = fieldNames;
    }

    @PostConstruct
    public void init(){
        authorizeChecker.checkAccess(false);
        responses = responseService.getAll();
        for(Response r : responses){
            r.getQuestionnaires().sort(Comparator.comparing(o -> o.getFieldName().getId()));
        }
        fieldNames = fieldNameService.getAll();
        for(Response r : responses) {
            BeanUtil.fillResponse(r,fieldNames);
        }
    }
}
