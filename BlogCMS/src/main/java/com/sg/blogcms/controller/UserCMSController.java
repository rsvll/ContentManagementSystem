/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.controller;

import com.sg.blogcms.dto.User;
import com.sg.blogcms.service.UserCMSService;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Brandon Mathura
 */
@Controller
public class UserCMSController {
    
    UserCMSService userService;
    PasswordEncoder encoder;
    
    
    
    @Inject
    public UserCMSController(UserCMSService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
        
    }
    @RequestMapping(value = "/displayUserProfile", method = RequestMethod.GET)
    public String getUserProfile(Model model, HttpServletRequest request ) { //@PathVariable int userId, @PathVariable String viewType
        String viewType = request.getParameter("viewType");
        String username = (request.getParameter("username"));
        
        User user = userService.selectUserByUsername(username);
        
        
        model.addAttribute("viewType",viewType);
        
        model.addAttribute("user", user);
        return "userprofile";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }
    
    
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String displayUserList(Map<String, Object> model) {
        List<User> users = userService.selectAllUsers();
        model.put("users", users);
        return "users";
    }
    
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("userId") int userId,
            Map<String, Object> model) {
        User user = userService.selectUser(userId);
        userService.deleteUser(user.getUsername());
        
        return "redirect:users";
    }
    
    @RequestMapping(value = "/chooseUser", method = RequestMethod.GET)
    public String chooseUserToUpdate(HttpServletRequest request, Model model) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String viewType = request.getParameter("viewType");
        model.addAttribute("viewType",viewType);
        User user = userService.selectUser(userId);
        model.addAttribute("user",user);
        model.addAttribute("userId",userId);
        List<User> users;
        users = userService.selectAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    
    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public String updateUser(HttpServletRequest request, Model model) {
        
            String username = request.getParameter("username")  ;
            User user = userService.selectUserByUsername(username);
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setUsername(request.getParameter("username"));
            user.setUserEmail(request.getParameter("email"));
            String clearPw = request.getParameter("password");
            String hashPw = encoder.encode(clearPw);
            user.setPassword(hashPw);
            user.setUserBio(request.getParameter("userBio"));
            
            if (null != request.getParameter("isAdmin")) {
                user.addAuthority("ROLE_ADMIN");
                user.setIsAdmin(true);
            }else{
                user.setIsAdmin(false);
            }
            
            if (null != request.getParameter("isEnabled")) {
                user.setIsEnabled(true);
            }else{
                user.setIsEnabled(false);
            }
            
            userService.updateUser(user);
            
            List<User> users = userService.selectAllUsers();
            model.addAttribute("users", users);
        
        
        return "users";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request) {
        
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setUsername(request.getParameter("username"));
        user.setUserEmail(request.getParameter("email"));
        String clearPw = request.getParameter("password");
        String hashPw = encoder.encode(clearPw);
        user.setPassword(hashPw);
        user.setUserBio(request.getParameter("userBio"));
        user.setIsEnabled(true);
        if (null != request.getParameter("isAdmin")) {
            user.addAuthority("ROLE_ADMIN");
            user.setIsAdmin(true);
        }else{
            user.setIsAdmin(false);
        }

        userService.createUser(user);
        

        return "redirect:users";
    }
}
    