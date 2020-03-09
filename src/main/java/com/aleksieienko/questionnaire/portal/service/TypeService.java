package com.aleksieienko.questionnaire.portal.service;

import com.aleksieienko.questionnaire.portal.db.entity.Type;
import java.util.List;

public interface TypeService {
    void add(Type type);
    List<Type> getAll();
    Type getById(Integer id);
    Type getByName(String name);
    boolean remove(Type type);
}
