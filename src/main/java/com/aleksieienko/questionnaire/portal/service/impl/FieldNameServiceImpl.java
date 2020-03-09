package com.aleksieienko.questionnaire.portal.service.impl;

import com.aleksieienko.questionnaire.portal.db.dao.FieldNameDao;
import com.aleksieienko.questionnaire.portal.db.entity.FieldName;
import com.aleksieienko.questionnaire.portal.service.FieldNameService;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@ApplicationScoped
public class FieldNameServiceImpl implements FieldNameService {
    @ManagedProperty(value = "#{fieldNameDaoImpl}")
    private FieldNameDao fieldNamesDao;

    public void setFieldNamesDao(FieldNameDao fieldNamesDao) {
        this.fieldNamesDao = fieldNamesDao;
    }

    @Override
    public void add(FieldName fieldName) {
        fieldNamesDao.add(fieldName);
    }

    @Override
    public List<FieldName> getAll() {
        return fieldNamesDao.getAll();
    }

    @Override
    public FieldName getByName(String name) {
        return fieldNamesDao.getByName(name);
    }
}
