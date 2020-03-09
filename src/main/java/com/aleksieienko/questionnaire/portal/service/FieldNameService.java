package com.aleksieienko.questionnaire.portal.service;

import com.aleksieienko.questionnaire.portal.db.entity.FieldName;
import java.util.List;

public interface FieldNameService {
    void add(FieldName fieldName);
    List<FieldName> getAll();
    FieldName getByName(String name);
}
