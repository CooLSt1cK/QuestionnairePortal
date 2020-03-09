package com.aleksieienko.questionnaire.portal.db.dao;

import com.aleksieienko.questionnaire.portal.db.entity.Questionnaire;
import java.util.List;

public interface QuestionnaireDao {
    void add(Questionnaire questionnaire);
    boolean addList(List<Questionnaire> list);
}
