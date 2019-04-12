/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dao;

import com.sg.blogcms.dto.User;
import com.sg.blogcms.mappers.UserMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author svlln
 */
public class UserCMSDaoDbImpl implements UserCMSDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //==========================================================================
    //                          SQL USER
    //==========================================================================
    private static final String SQL_SELECT_USER
            = "select * from User where idUser = ? ";
    
    private static final String SQL_SELECT_USER_BY_USERNAME
            = "select * from User where username = ? ";

    private static final String SQL_DELETE_USER_BY_ID
            = "delete from User where idUser = ?";
    
    private static final String SQL_DELETE_USER
        = "delete from User where username = ?";
    
    private static final String SQL_DELETE_AUTHORITIES
        = "delete from authorities where username = ?";

    private static final String SQL_SELECT_ALL_USERS
            = "select * from User";

    private static final String SQL_UPDATE_USER
            = "update User set firstName = ?, lastName = ?, username = ?, userEmail = ?, password = ?, bio = ?, isAdmin = ? ,enabled = ? where idUser = ?";

    private static final String SQL_INSERT_USER
            = "insert into User (firstName, lastName, username, userEmail, password, bio, isAdmin,enabled) values(?,?,?,?,?,?,?,?)";
    
    private static final String SQL_INSERT_AUTHORITY
        = "insert into Authorities (username, authority) values (?, ?)";
    
    
    
    
    

    //==========================================================================
    //                                 METHODS
    //==========================================================================
    @Override
    public User selectUser(int userID) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_USER,
                    new UserMapper(),
                    userID);
            user.addAuthority("ROLE_USER");
            if(user.getIsAdmin()==true){
                user.addAuthority("ROLE_ADMIN");           
            }
            return user;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public User selectUserByUsername(String username){
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_USERNAME,
                    new UserMapper(),
                    username);
            user.addAuthority("ROLE_USER");
            if(user.getIsAdmin()==true){
                user.addAuthority("ROLE_ADMIN");           
            }
            return user;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> userList = jdbcTemplate.query(SQL_SELECT_ALL_USERS,
                new UserMapper());
        for(User user: userList){
            
            if(user.getIsAdmin()){
                user.addAuthority("ROLE_ADMIN");           
            }
        }
        return userList;
    }

    @Override
    public void removeUser(int userID) {
        
        
        jdbcTemplate.update(SQL_DELETE_USER_BY_ID, userID);
    }

    @Override
    public User updateUser(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getUserEmail(),
                user.getPassword(),
                user.getUserBio(),
                user.getIsAdmin(),
                user.getIsEnabled(),
                user.getUserId());
        
        if(user.getIsAdmin()){
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, 
                                user.getUsername(), 
                                "ROLE_ADMIN");
        }
        
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User createUser(User user) {
        jdbcTemplate.update(SQL_INSERT_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getUserEmail(),
                user.getPassword(),
                user.getUserBio(),
                user.getIsAdmin(),
                user.getIsEnabled());

        int userId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);
        user.setUserId(userId);
        
        ArrayList<String> authorities = (ArrayList<String>) user.getAuthorityList();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, 
                                user.getUsername(), 
                                authority);
        }
        
        return user;
    }
    
    @Override
    public void deleteUser(String username) {
        // first delete all authorities for this user
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, username);
        // second delete the user
        jdbcTemplate.update(SQL_DELETE_USER, username);
    }
}
