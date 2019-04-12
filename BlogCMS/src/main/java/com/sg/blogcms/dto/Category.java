/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author svlln
 */
public class Category {
    private int catId;
    private String catName;
    private String description;
    private List<User> associatedUserList;
    private List<BlogPost> associatedBlogs;

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getAssociatedUserList() {
        return associatedUserList;
    }

    public void setAssociatedUserList(List<User> associatedUserList) {
        this.associatedUserList = associatedUserList;
    }

    public List<BlogPost> getAssociatedBlogs() {
        return associatedBlogs;
    }

    public void setAssociatedBlogs(List<BlogPost> associatedBlogs) {
        this.associatedBlogs = associatedBlogs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.catId;
        hash = 79 * hash + Objects.hashCode(this.catName);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.associatedUserList);
        hash = 79 * hash + Objects.hashCode(this.associatedBlogs);
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
        final Category other = (Category) obj;
        if (this.catId != other.catId) {
            return false;
        }
        if (!Objects.equals(this.catName, other.catName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.associatedUserList, other.associatedUserList)) {
            return false;
        }
        if (!Objects.equals(this.associatedBlogs, other.associatedBlogs)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
