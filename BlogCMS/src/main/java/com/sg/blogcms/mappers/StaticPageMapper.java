/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.mappers;

import com.sg.blogcms.dto.StaticPage;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author josesosa
 */
public class StaticPageMapper implements RowMapper<StaticPage>{
         @Override
         public StaticPage mapRow(ResultSet rs, int rowNum) throws SQLException{
            StaticPage sp = new StaticPage();
            
            sp.setId(rs.getInt("idStaticPage"));
            sp.setTitle(rs.getString("title"));
            sp.setDescription(rs.getString("description"));
            sp.setContent(rs.getString("content"));
            sp.setAuthor(rs.getString("author"));
            sp.setCreatedDate((rs.getTimestamp("dateCreated")));
            sp.setPublishDate((rs.getTimestamp("publishedDate")));
            sp.setExpirationDate((rs.getTimestamp("expirationDate")));
            sp.setIsActive(rs.getBoolean("isActive"));
            sp.setUserId(rs.getInt("idUser"));
            return sp;
         }
     }