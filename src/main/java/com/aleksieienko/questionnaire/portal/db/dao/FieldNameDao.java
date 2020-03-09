package com.aleksieienko.questionnaire.portal.db.dao;

import com.aleksieienko.questionnaire.portal.db.entity.FieldName;
import java.util.List;

public interface FieldNameDao {
    void add(FieldName fieldName);
    List<FieldName> getAll();
    FieldName getByName(String name);
}
