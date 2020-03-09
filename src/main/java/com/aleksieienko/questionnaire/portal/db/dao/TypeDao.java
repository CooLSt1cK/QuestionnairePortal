package com.aleksieienko.questionnaire.portal.db.dao;

import com.aleksieienko.questionnaire.portal.db.entity.Type;
import java.util.List;

public interface TypeDao {
    void add(Type type);
    List<Type> getAll();
    Type getById(Integer id);
    Type getByName(String name);
    boolean removeType(Type type);
}
