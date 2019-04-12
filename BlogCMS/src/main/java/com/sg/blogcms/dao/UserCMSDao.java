/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dao;

import com.sg.blogcms.dto.User;
import java.util.List;

/**
 *
 * @author svlln
 */
public interface UserCMSDao {

    public User selectUser(int userID);

    public List<User> selectAllUsers();

    void removeUser(int userID);

    public User updateUser(User user);

    public User createUser(User user);
    
    void deleteUser(String username);

    public User selectUserByUsername(String username);

}
