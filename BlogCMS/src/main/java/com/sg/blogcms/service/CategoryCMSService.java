/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.service;

import com.sg.blogcms.dao.CategoryCMSDao;
import com.sg.blogcms.dto.Category;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author josesosa
 */
public class CategoryCMSService {

    CategoryCMSDao catDao;
    
    @Inject
    public CategoryCMSService(CategoryCMSDao catDao) {
        this.catDao = catDao;
    }
    
    public List<Category> selectAllCategories() {
        return catDao.selectAllCategories();
    }
    
    public void removeCategory(int catID) {
        catDao.removeCategory(catID);
    }
    
    public Category updateCategory(Category cat) {
     return catDao.updateCategory(cat);
    }

    public Category selectCatById(int catID) {
        return catDao.selectCategory(catID);
    }

    public Category createCategory(Category cat) {
        return catDao.createCategory(cat);
    }
    
}
