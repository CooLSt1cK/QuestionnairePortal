package com.aleksieienko.questionnaire.portal.service.impl;

import com.aleksieienko.questionnaire.portal.db.dao.UserDao;
import com.aleksieienko.questionnaire.portal.db.entity.User;
import com.aleksieienko.questionnaire.portal.db.entity.UserUtil;
import com.aleksieienko.questionnaire.portal.service.FieldPatterns;
import com.aleksieienko.questionnaire.portal.service.UserService;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@ApplicationScoped
public class UserServiceImpl implements UserService {
    @ManagedProperty(value = "#{userDaoImpl}")
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public User getByEmail(String email) {
        if(email.matches(FieldPatterns.USER_EMAIL)){
            return userDao.getByEmail(email);
        }
        return null;
    }

    @Override
    public void add(User user, String password) {
        if(userIsValid(user) && password.matches(FieldPatterns.USER_PASSWORD) && UserUtil.fillPassword(user,password)) {
            userDao.addUser(user);
        }
    }

    @Override
    public void add(User user) {
        userDao.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        if(userIsValid(user)){
            return userDao.updateUser(user);
        }
        return false;
    }

    @Override
    public boolean changePassword(User user, String newPassword, String currentPassword) {
        try {
            if(UserUtil.getHashedPassword(currentPassword,user.getSalt()).equals(user.getHash())){
                UserUtil.fillPassword(user,newPassword);
                return userDao.updateUser(user);
            }
        } catch (InvalidKeySpecException|NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean removeUser(User user) {
        return userDao.deleteUser(user);
    }

    private boolean userIsValid(User user){
        return (user.getEmail().matches(FieldPatterns.USER_EMAIL)
                && (user.getFirstName()==null || user.getFirstName().matches(FieldPatterns.USER_NAME))
                && (user.getLastName()==null || user.getLastName().matches(FieldPatterns.USER_NAME))
                && (user.getPhone()==null || user.getPhone().matches(FieldPatterns.USER_PHONE)));
    }
}
