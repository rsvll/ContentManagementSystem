/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dao;

import com.sg.blogcms.dto.Category;
import java.util.List;

/**
 *
 * @author svlln
 */
public interface CategoryCMSDao {
     
    public List<Category> selectAllCategories();
    void removeCategory(int catID);
    public Category updateCategory(Category cat);

    public Category selectCategory(int catID);

    public Category createCategory(Category cat);
}
