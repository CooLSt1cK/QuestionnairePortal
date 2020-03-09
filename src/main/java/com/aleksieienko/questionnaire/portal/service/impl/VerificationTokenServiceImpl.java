package com.aleksieienko.questionnaire.portal.service.impl;

import com.aleksieienko.questionnaire.portal.db.dao.VerificationTokenDao;
import com.aleksieienko.questionnaire.portal.db.entity.User;
import com.aleksieienko.questionnaire.portal.db.entity.VerificationToken;
import com.aleksieienko.questionnaire.portal.service.VerificationTokenService;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@ApplicationScoped
public class VerificationTokenServiceImpl implements VerificationTokenService {
    @ManagedProperty(value="#{verificationTokenDaoImpl}")
    private VerificationTokenDao verificationTokenDao;

    public void setVerificationTokenDao(VerificationTokenDao verificationTokenDao) {
        this.verificationTokenDao = verificationTokenDao;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        verificationTokenDao.createVerificationToken(user, token);
    }

    @Override
    public VerificationToken getTokenById(Integer id) {
        return verificationTokenDao.getTokenById(id);
    }

    @Override
    public VerificationToken getTokenByToken(String token) {
        return verificationTokenDao.getTokenByToken(token);
    }

    @Override
    public boolean remove(VerificationToken verificationToken) {
        return verificationTokenDao.remove(verificationToken);
    }
}
