package com.aleksieienko.questionnaire.portal.service.impl;

import com.aleksieienko.questionnaire.portal.db.dao.QuestionnaireDao;
import com.aleksieienko.questionnaire.portal.db.entity.Questionnaire;
import com.aleksieienko.questionnaire.portal.service.QuestionnaireService;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@ApplicationScoped
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @ManagedProperty(value = "#{questionnaireDaoImpl}")
    private QuestionnaireDao questionnaireDao;

    public void setQuestionnaireDao(QuestionnaireDao questionnaireDao) {
        this.questionnaireDao = questionnaireDao;
    }

    @Override
    public void add(Questionnaire questionnaire) {
        questionnaireDao.add(questionnaire);
    }

    @Override
    public boolean addList(List<Questionnaire> list) {
        return questionnaireDao.addList(list);
    }
}
