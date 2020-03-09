package com.aleksieienko.questionnaire.portal.service;

import com.aleksieienko.questionnaire.portal.db.entity.Response;
import java.util.List;

public interface ResponseService {
    void add(Response response);
    List<Response> getAll();
}
