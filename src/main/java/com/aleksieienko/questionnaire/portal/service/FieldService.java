package com.aleksieienko.questionnaire.portal.service;

import com.aleksieienko.questionnaire.portal.db.entity.Field;
import java.util.List;

public interface FieldService {
    void add(Field field);
    List<Field> getAll();
    List<Field> getActive();
    Field getById(Integer id);
    Field getByLabel(String label);
    boolean update(Field field);
    boolean remove(Field field);
}
