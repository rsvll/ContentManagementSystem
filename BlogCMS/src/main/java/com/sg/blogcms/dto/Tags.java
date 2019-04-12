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
public class Tags {
    private int tagId;
    private String tagName;
    private String description;
    private List<BlogPost> associatedBlogs;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash = 97 * hash + this.tagId;
        hash = 97 * hash + Objects.hashCode(this.tagName);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.associatedBlogs);
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
        final Tags other = (Tags) obj;
        if (this.tagId != other.tagId) {
            return false;
        }
        if (!Objects.equals(this.tagName, other.tagName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.associatedBlogs, other.associatedBlogs)) {
            return false;
        }
        return true;
    }
    
    
}
