package com.aleksieienko.questionnaire.portal.db.dao;

import com.aleksieienko.questionnaire.portal.db.entity.User;
import com.aleksieienko.questionnaire.portal.db.entity.VerificationToken;

public interface VerificationTokenDao {
    void createVerificationToken(User user, String token);
    VerificationToken getTokenById(Integer id);
    VerificationToken getTokenByToken(String token);
    boolean remove(VerificationToken verificationToken);
}
