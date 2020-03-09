package com.aleksieienko.questionnaire.portal.service;

import com.aleksieienko.questionnaire.portal.db.entity.User;
import com.aleksieienko.questionnaire.portal.db.entity.VerificationToken;

public interface VerificationTokenService {
void createVerificationToken(User user, String token);
VerificationToken getTokenById(Integer id);
VerificationToken getTokenByToken(String token);
boolean remove(VerificationToken verificationToken);
}
