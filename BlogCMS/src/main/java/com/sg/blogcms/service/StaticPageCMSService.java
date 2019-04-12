/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.service;

import com.sg.blogcms.dao.StaticPageCMSDao;
import com.sg.blogcms.dto.Category;
import com.sg.blogcms.dto.StaticPage;
import com.sg.blogcms.dto.Tags;
import com.sg.blogcms.dto.User;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author josesosa
 */
public class StaticPageCMSService {
    StaticPageCMSDao spDao;
    
    
    @Inject
    public StaticPageCMSService(StaticPageCMSDao spDao) {
        this.spDao = spDao;
    }

    public List<StaticPage> selectAllStaticPages() {
        return spDao.selectAllStaticPages();
    }
    
    public StaticPage selectStaticPage(int spId) {
     return spDao.selectStaticPage(spId);
    }
    
    public List<Tags> selectAllTags() {
     return spDao.selectAllTags();
    }
    
    public List<Category> selectAllCategories() {
       return spDao.selectAllCategories();
    }

    public User selectUserByUsername(String username) {
        return spDao.selectUserByUsername(username);
    }

    public StaticPage createStaticPage(StaticPage sp) {
        return spDao.createStaticPage(sp);
    }

    public void approveStaticPage(int spId) {
        spDao.approveStaticPage(spId);
    }

    public void deleteStaticPage(int spId) {
        spDao.deleteStaticPage(spId);
    }

    public void updateStaticPage(StaticPage sp) {
        spDao.updateStaticPage(sp);
    }
    
}
