/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dao;

import com.sg.blogcms.dto.Category;
import com.sg.blogcms.dto.StaticPage;
import com.sg.blogcms.dto.Tags;
import com.sg.blogcms.dto.User;
import java.util.List;

/**
 *
 * @author svlln
 */
public interface StaticPageCMSDao {
    StaticPage createStaticPage(StaticPage sp);
    StaticPage updateStaticPage(StaticPage sp);
    StaticPage selectStaticPage(int spId);
    List<StaticPage> selectAllStaticPages();
    List<StaticPage> selectAllStaticPagesByUser(int userID);
    public List<Tags> selectAllTags();    
    public List<Category> selectAllCategories();

    public User selectUserByUsername(String username);

    public void approveStaticPage(int spId);

    public void deleteStaticPage(int spId);
    
}
