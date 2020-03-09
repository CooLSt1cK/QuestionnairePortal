package com.aleksieienko.questionnaire.portal.service;

import com.aleksieienko.questionnaire.portal.db.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Integer id);
    User getByEmail(String email);
    void add(User user, String password);
    void add(User user);
    boolean updateUser(User user);
    boolean changePassword(User user,String newPassword, String currentPassword);
    boolean removeUser(User user);
}
