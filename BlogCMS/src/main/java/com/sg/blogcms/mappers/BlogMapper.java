/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.mappers;

import com.sg.blogcms.dto.BlogPost;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author josesosa
 */
public class BlogMapper implements RowMapper<BlogPost>{
         @Override
         public BlogPost mapRow(ResultSet rs, int rowNum) throws SQLException{
            BlogPost bp = new BlogPost();
            
            bp.setId(rs.getInt("idBlogPost"));
            bp.setTitle(rs.getString("title"));
            bp.setDescription(rs.getString("description"));
            bp.setContent(rs.getString("content"));
            bp.setAuthor(rs.getString("author"));
            bp.setCreatedDate((rs.getTimestamp("createdDate")));
            bp.setPublishDate((rs.getTimestamp("publishDate")));
            bp.setExpirationDate((rs.getTimestamp("expirationDate")));
            bp.setIsApproved(rs.getBoolean("approved"));
            bp.setCatId(rs.getInt("idCategories"));
            bp.setUserId(rs.getInt("idUser"));
//          
//            cat.setCatId(rs.getInt("idCategories"));
//            cat.setCatName(rs.getString("categoryName"));
//            cat.setDescription(rs.getString("categoryDescription"));
//            bp.setCategory(cat);
            
            return bp;
         }
     }
