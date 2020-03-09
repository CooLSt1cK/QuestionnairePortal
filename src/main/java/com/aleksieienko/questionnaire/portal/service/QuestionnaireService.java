package com.aleksieienko.questionnaire.portal.service;

import com.aleksieienko.questionnaire.portal.db.entity.Questionnaire;
import java.util.List;

public interface QuestionnaireService {
    void add(Questionnaire questionnaire);
    boolean addList(List<Questionnaire> list);
}
