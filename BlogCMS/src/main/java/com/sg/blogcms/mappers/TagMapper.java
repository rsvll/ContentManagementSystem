/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.mappers;

import com.sg.blogcms.dto.Tags;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author josesosa
 */
public class TagMapper implements RowMapper<Tags> {
     @Override
         public Tags mapRow(ResultSet rs, int rowNum) throws SQLException{
            Tags tags = new Tags();
            
            tags.setTagId(rs.getInt("idTag"));
            tags.setTagName(rs.getString("tagName"));
            tags.setDescription(rs.getString("tagDescription"));

            
            return tags;
         }
}