/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.service;

import com.sg.blogcms.dao.UserCMSDao;
import com.sg.blogcms.dto.User;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author josesosa
 */
public class UserCMSService {
    UserCMSDao userDao;
    
//    public List<User> selectAllUsers();
//
//    void removeUser(int userID);
//
//    public User updateUser(User user);
//
//    public User createUser(User user);
    
    @Inject
    public UserCMSService(UserCMSDao userDao) {
        this.userDao = userDao;
    }

    public User selectUser(int userID) {
        return userDao.selectUser(userID);
    }
    
    public List<User> selectAllUsers() {
        return userDao.selectAllUsers();
    }
    

    public void removeUser(int userID) {
      userDao.removeUser(userID);
    }


    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    public User createUser(User user) {
        return userDao.createUser(user);
    }

    public User selectUserByUsername(String username) {
        return userDao.selectUserByUsername(username);
    }

    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }


}
