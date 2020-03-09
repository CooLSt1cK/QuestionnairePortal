package com.aleksieienko.questionnaire.portal.service.impl;

import com.aleksieienko.questionnaire.portal.db.dao.FieldDao;
import com.aleksieienko.questionnaire.portal.db.entity.Field;
import com.aleksieienko.questionnaire.portal.service.FieldService;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@ApplicationScoped
public class FieldServiceImpl implements FieldService {
    @ManagedProperty(value = "#{fieldDaoImpl}")
    private FieldDao fieldDao;

    public void setFieldDao(FieldDao fieldDao) {
        this.fieldDao = fieldDao;
    }

    @Override
    public void add(Field field) {
        if(field.isValid()) {
            fieldDao.add(field);
        }
    }

    @Override
    public List<Field> getAll() {
        return fieldDao.getAll();
    }

    @Override
    public List<Field> getActive() {
        return fieldDao.getActive();
    }

    @Override
    public Field getById(Integer id) {
        return fieldDao.getById(id);
    }

    @Override
    public Field getByLabel(String label) {
        return fieldDao.getByLabel(label);
    }

    @Override
    public boolean update(Field field) {
        return fieldDao.update(field);
    }

    @Override
    public boolean remove(Field field) {
        return fieldDao.remove(field);
    }
}
