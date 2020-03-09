package com.aleksieienko.questionnaire.portal.db.dao;

import com.aleksieienko.questionnaire.portal.db.entity.Response;
import java.util.List;

public interface ResponseDao {
    void add(Response response);
    List<Response> getAll();
}
