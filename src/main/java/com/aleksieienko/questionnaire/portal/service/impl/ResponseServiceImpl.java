package com.aleksieienko.questionnaire.portal.service.impl;

import com.aleksieienko.questionnaire.portal.db.dao.ResponseDao;
import com.aleksieienko.questionnaire.portal.db.entity.Response;
import com.aleksieienko.questionnaire.portal.service.ResponseService;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@ApplicationScoped
public class ResponseServiceImpl implements ResponseService {
    @ManagedProperty(value = "#{responseDaoImpl}")
    private ResponseDao responseDao;

    public void setResponseDao(ResponseDao responseDao) {
        this.responseDao = responseDao;
    }

    @Override
    public void add(Response response) {
        responseDao.add(response);
    }

    @Override
    public List<Response> getAll() {
        return responseDao.getAll();
    }
}
