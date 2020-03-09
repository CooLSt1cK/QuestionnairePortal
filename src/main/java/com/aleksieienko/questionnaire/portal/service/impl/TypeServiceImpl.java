package com.aleksieienko.questionnaire.portal.service.impl;

import com.aleksieienko.questionnaire.portal.db.dao.TypeDao;
import com.aleksieienko.questionnaire.portal.db.entity.Type;
import com.aleksieienko.questionnaire.portal.service.TypeService;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@ApplicationScoped
public class TypeServiceImpl implements TypeService {
    @ManagedProperty(value = "#{typeDaoImpl}")
    private TypeDao typeDao;

    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    @Override
    public void add(Type type) {
        typeDao.add(type);
    }

    @Override
    public List<Type> getAll() {
        return typeDao.getAll();
    }

    @Override
    public Type getById(Integer id) {
        return typeDao.getById(id);
    }

    @Override
    public Type getByName(String name) {
        return typeDao.getByName(name);
    }

    @Override
    public boolean remove(Type type) {
        return typeDao.removeType(type);
    }
}
