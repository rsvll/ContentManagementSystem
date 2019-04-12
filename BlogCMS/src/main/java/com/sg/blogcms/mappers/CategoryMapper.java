/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.mappers;

import com.sg.blogcms.dto.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author josesosa
 */
public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException{
       Category cat = new Category();

       cat.setCatId(rs.getInt("idCategories"));
       cat.setCatName(rs.getString("categoryName"));
       cat.setDescription(rs.getString("categoryDescription"));

       return cat;
    }
    
}

