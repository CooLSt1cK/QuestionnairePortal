package com.aleksieienko.questionnaire.portal.db.dao;

import com.aleksieienko.questionnaire.portal.db.entity.User;
import java.util.List;

public interface UserDao {
    List<User> getAll();
    User getById(Integer id);
    User getByEmail(String email);
    void addUser(User user);
    boolean deleteUser(User user);
    boolean updateUser(User user);
}
