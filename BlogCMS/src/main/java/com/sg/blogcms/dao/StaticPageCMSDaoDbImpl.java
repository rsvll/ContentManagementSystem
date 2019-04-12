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
import com.sg.blogcms.mappers.CategoryMapper;
import com.sg.blogcms.mappers.StaticPageMapper;
import com.sg.blogcms.mappers.TagMapper;
import com.sg.blogcms.mappers.UserMapper;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author svlln
 */
public class StaticPageCMSDaoDbImpl implements StaticPageCMSDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//    ----------------------------------------------------------------------------
//    **********************PREPARED STATEMENTS **********************************
//    ----------------------------------------------------------------------------

    private static final String SQL_SELECT_STATIC_PAGE
            = "select * from StaticPage where idStaticPage = ? ";
    
    private static final String SQL_SELECT_USER_BY_USERNAME
            = "select * from User where username = ? ";
    
    private static final String SQL_SELECT_ALL_STATIC_PAGES = 
            "select * from StaticPage where isActive = 1";
    
    private static final String SQL_SELECT_ALL_TAGS
            = "select * from Tag";

    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from Categories";
    
    private static final String SQL_INSERT_STATIC_PAGE
            = "insert into StaticPage (title, description, content, author, dateCreated, " 
            + "publishedDate, expirationDate,isActive,idUser ) values(?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_APPROVE_STATIC_PAGE
            = "update StaticPage set isActive = 1 where idStaticPage = ?";
    
    private static final String SQL_DELETE_SP
            = "delete from StaticPage where idStaticPage = ?";
    
    private static final String SQL_UPDATE_SP
            = "update StaticPage set title = ?, description = ?  ,content = ?, author = ?,"
                + " dateCreated = ?, publishedDate = ?, expirationDate = ?, isActive = ?, idUser = ?  where idStaticPage =?";
    

//    ----------------------------------------------------------------------------
//    *******************INTERFACE METHODS****************************************
//    ----------------------------------------------------------------------------
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public StaticPage createStaticPage(StaticPage sp) {
        jdbcTemplate.update(SQL_INSERT_STATIC_PAGE,
                sp.getTitle(),
                sp.getDescription(),
                sp.getContent(),
                sp.getAuthor(),
                sp.getCreatedDate(),
                sp.getPublishDate(),
                sp.getExpirationDate(),
                sp.getIsActive(),
                sp.getUserId());
        int spId = 
                jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                             Integer.class);

        sp.setId(spId);
        return sp; 
    }
    
    @Override
    public User selectUserByUsername(String username){
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_USERNAME,
                    new UserMapper(),
                    username);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void deleteStaticPage(int spId) {
        jdbcTemplate.update(SQL_DELETE_SP, spId);
    }

    @Override
    public StaticPage updateStaticPage(StaticPage sp) {
    jdbcTemplate.update(SQL_UPDATE_SP,
                sp.getTitle(),
                sp.getDescription(),
                sp.getContent(),
                sp.getAuthor(),
                sp.getCreatedDate(),
                sp.getPublishDate(),
                sp.getExpirationDate(),
                sp.getIsActive(),
                sp.getUserId(),
                sp.getId());
         return sp;
    }

    @Override
    public StaticPage selectStaticPage(int spId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_STATIC_PAGE,
                    new StaticPageMapper(),
                    spId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<StaticPage> selectAllStaticPages() {
        return jdbcTemplate.query(SQL_SELECT_ALL_STATIC_PAGES,
                new StaticPageMapper());
    }

    @Override
    public List<StaticPage> selectAllStaticPagesByUser(int userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Tags> selectAllTags(){
        return jdbcTemplate.query(SQL_SELECT_ALL_TAGS,
                new TagMapper());
        
    }
    @Override
    public List<Category> selectAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES,
                new CategoryMapper());
    }
    
    @Override
    public void approveStaticPage(int spId){
        jdbcTemplate.update(SQL_APPROVE_STATIC_PAGE,
               spId);
    }
    
    
    
//    ----------------------------------------------------------------------------
//    ********************HELPER METHODS*********************************************
//    ----------------------------------------------------------------------------


}
