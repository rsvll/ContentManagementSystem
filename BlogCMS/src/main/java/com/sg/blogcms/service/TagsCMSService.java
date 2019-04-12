/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.service;

import com.sg.blogcms.dao.TagsCMSDao;
import com.sg.blogcms.dto.Tags;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author josesosa
 */
public class TagsCMSService {
    TagsCMSDao tagDao;
    
    
    @Inject
    public TagsCMSService(TagsCMSDao tagDao) {
        this.tagDao = tagDao;
    }
    
    public Tags createTag(Tags tag){
        return tagDao.createTag(tag);
    }
    
    public void removeTag(int tagID){
        tagDao.removeTag(tagID);
    }
    
    public Tags updateTag(Tags tag){
        return tagDao.updateTag(tag);
    }
    
    public List<Tags> SelectAllTags(){
        return tagDao.SelectAllTags();
    }
    
    public Tags SelectTag (int tagID){
        return tagDao.SelectTag(tagID);
    } 
    
}
