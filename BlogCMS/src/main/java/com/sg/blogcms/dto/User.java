/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dto;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author svlln
 */
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String userEmail;
    private String password;
    private String userBio;
    private Boolean isAdmin;
    private Boolean isEnabled;
    private List<BlogPost> associatedBlogs;
    private List<StaticPage> associatedStaticPages;
    private List<Category> associatedCategories;
    private List<String> authorityList = new ArrayList<>(asList("ROLE_USER"));

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public List<BlogPost> getAssociatedBlogs() {
        return associatedBlogs;
    }

    public void setAssociatedBlogs(List<BlogPost> associatedBlogs) {
        this.associatedBlogs = associatedBlogs;
    }

    public List<StaticPage> getAssociatedStaticPages() {
        return associatedStaticPages;
    }

    public void setAssociatedStaticPages(List<StaticPage> associatedStaticPages) {
        this.associatedStaticPages = associatedStaticPages;
    }

    public List<Category> getAssociatedCategories() {
        return associatedCategories;
    }

    public void setAssociatedCategories(List<Category> associatedCategories) {
        this.associatedCategories = associatedCategories;
    }

    public List<String> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<String> authorityList) {
        this.authorityList = authorityList;
    }
    
    public void addAuthority(String role){
        authorityList.add(role);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.userId;
        hash = 67 * hash + Objects.hashCode(this.firstName);
        hash = 67 * hash + Objects.hashCode(this.lastName);
        hash = 67 * hash + Objects.hashCode(this.username);
        hash = 67 * hash + Objects.hashCode(this.userEmail);
        hash = 67 * hash + Objects.hashCode(this.password);
        hash = 67 * hash + Objects.hashCode(this.userBio);
        hash = 67 * hash + Objects.hashCode(this.isEnabled);
        hash = 67 * hash + Objects.hashCode(this.associatedBlogs);
        hash = 67 * hash + Objects.hashCode(this.associatedStaticPages);
        hash = 67 * hash + Objects.hashCode(this.associatedCategories);
        hash = 67 * hash + Objects.hashCode(this.authorityList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.userEmail, other.userEmail)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.userBio, other.userBio)) {
            return false;
        }
        if (!Objects.equals(this.isEnabled, other.isEnabled)) {
            return false;
        }
        if (!Objects.equals(this.associatedBlogs, other.associatedBlogs)) {
            return false;
        }
        if (!Objects.equals(this.associatedStaticPages, other.associatedStaticPages)) {
            return false;
        }
        if (!Objects.equals(this.associatedCategories, other.associatedCategories)) {
            return false;
        }
        if (!Objects.equals(this.authorityList, other.authorityList)) {
            return false;
        }
        return true;
    }
    
    
}
