/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.dao;

import com.sg.blogcms.dto.BlogPost;
import com.sg.blogcms.dto.Category;
import com.sg.blogcms.dto.StaticPage;
import com.sg.blogcms.dto.Tags;
import com.sg.blogcms.dto.User;
import com.sg.blogcms.mappers.BlogMapper;
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
public class BlogsCMSDaoDbImpl implements BlogsCMSDao{
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    //==========================================================================
    //                          SQL BLOGPOST
    //==========================================================================

    private static final String SQL_INSERT_BLOG
            = "insert into BlogPost (title, description, content, author, createdDate, " 
            + "publishDate, expirationDate,approved,idUser ,idCategories) values(?,?,?,?,?,?,?,?,?,?)";
    
    private static final String SQL_INSERT_INTO_BLOGS_TAGS = " insert into BlogpostTag (idBlogPost, idTag) values (?,?)";
    
    private static final String SQL_DELETE_BLOG
            = "delete from BlogPost where idBlogPost = ?";
    
    private static final String SQL_UPDATE_BLOG
            = "update BlogPost set title = ?, description = ?  ,content = ?, author = ?,"
                + " createdDate = ?, publishDate = ?, expirationDate = ?, approved = ?, idUser = ?, idCategories = ? where idBlogPost =?";
    
    private static final String SQL_APPROVE_BLOG
            = "update BlogPost set approved = 1 where idBlogPost = ?";
    
    private static final String SQL_SELECT_BLOG
            = "select * from BlogPost where idBlogPost = ? ";
    
    private static final String SQL_SELECT_BLOG_BY_USER
            = "select * from BlogPost where idUser =? ";
    
    private static final String SQL_SELECT_BLOG_BY_CAT
            = "select * from BlogPost where idCategories = ?";
    
    private static final String SQL_SELECT_BLOG_BY_TAG
            = "select * from BlogPost where idTag = ?";
    
    private static final String SQL_SELECT_LAST_TEN_BLOGPOST
           = "select * FROM BlogPost where approved = 1 ORDER BY idBlogPost DESC LIMIT 10";
    
    private static final String SQL_SELECT_ALL_BLOGS = 
            "select * from BlogPost";
    
    private static final String SQL_SELECT_USER_BY_BLOG
            = "select * from User usr Join BlogPost bp on usr.idUser = bp.idUser where idBlogPost =? ";
    
    private static final String SQL_SELECT_USER_BY_USERNAME
            = "select * from User where username = ? ";
    
    private static final String SQL_SELECT_ALL_TAGS
            = "select * from Tag";

    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from Categories";
    
    private static final String SQL_SELECT_TAG_BY_ID
            = "select * from Tag where idTag = ?";
    
    private static final String SQL_SELECT_TAGS_BY_BLOGID = 
            "Select tg.`idTag`, tg.`tagName`, tg.`tagDescription` from Tag tg Join  BlogpostTag bpt on tg.idTag = bpt.idTag where idBlogPost = ?";
    
    
    private static final String SQL_SELECT_ALL_INACTIVE_STATIC_PAGES = 
            "select * from StaticPage where isActive = 0";
    
    private static final String SQL_DELETE_TAGS_RELATIONSHIP
        = "delete from BlogpostTag where idBlogPost = ?";
    
    
    //==========================================================================
    //                                 METHODS
    //==========================================================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public BlogPost createBlog(BlogPost blog) {
        jdbcTemplate.update(SQL_INSERT_BLOG,
                blog.getTitle(),
                blog.getDescription(),
                blog.getContent(),
                blog.getAuthor(),
                blog.getCreatedDate(),
                blog.getPublishDate(),
                blog.getExpirationDate(),
                blog.getIsApproved(),
                blog.getUserId(),
                blog.getCatId());
        int blogId = 
                jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                             Integer.class);

        blog.setId(blogId);
        return blog; 
    }

    @Override
    public void removeBlogPost(int blogID) {
        jdbcTemplate.update(SQL_DELETE_BLOG, blogID);
    }

    @Override
    public BlogPost updateBlog(BlogPost blog) {
         jdbcTemplate.update(SQL_UPDATE_BLOG,
                blog.getTitle(),
                blog.getDescription(),
                blog.getContent(),
                blog.getAuthor(),
                blog.getCreatedDate(),
                blog.getPublishDate(),
                blog.getExpirationDate(),
                blog.getIsApproved(),
                blog.getUserId(),
                blog.getCatId(),
                blog.getId());
         return blog;
    }

    @Override
    public BlogPost selectBlog(int blogID) {
         try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BLOG, 
                                               new BlogMapper(), 
                                               blogID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    

    @Override
    public List<BlogPost> selectAllBlogs() {
        return jdbcTemplate.query(SQL_SELECT_ALL_BLOGS,
                new BlogMapper());
    }
    
    @Override
    public void approveBlog(int bpId){
        jdbcTemplate.update(SQL_APPROVE_BLOG,
               bpId);
    }
    
    @Override
    public List<BlogPost> selectLastTenBlogs() {
       return jdbcTemplate.query(SQL_SELECT_LAST_TEN_BLOGPOST,
               new BlogMapper());
    }
    
    @Override
    public List<BlogPost> selectAllBlogsByUser(int userID) {
       List<BlogPost> blogByUserList = 
                jdbcTemplate.query(SQL_SELECT_BLOG_BY_USER, 
                                   new BlogMapper(), 
                                   userID);
        // set the publisher and list of Authors for each book
       //return associateBlogAndUser(blogByUserList);
       return null;
    }

    @Override
    public List<BlogPost> selectAllBlogsByCategory(int catID) {
        return null;
    }

    @Override
    public List<BlogPost> selectAllBlogsByTag(int tagID) {
        return null;
    }
    
    @Override
    public List<Tags> selectAllTags(){
        return jdbcTemplate.query(SQL_SELECT_ALL_TAGS,
                new TagMapper());
        
    }
    
    @Override
    public List<Tags> selectTagsByBlogId(int blogId){
        return jdbcTemplate.query(SQL_SELECT_TAGS_BY_BLOGID,
                new TagMapper(),
                blogId);
        
    }
    
    @Override
    public List<Category> selectAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES,
                new CategoryMapper());
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
    public Tags selectTagById(int tagID) {
        try{
            return jdbcTemplate.queryForObject(SQL_SELECT_TAG_BY_ID, 
                    new TagMapper(),
                    tagID);
        } catch(EmptyResultDataAccessException ex){
            return null;
        }
    }
    
    
    
    @Override
    public List<StaticPage> selectAllInactiveStaticPages() {
        return jdbcTemplate.query(SQL_SELECT_ALL_INACTIVE_STATIC_PAGES,
                new StaticPageMapper());
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateBlogAndTag(String[] tagIds, BlogPost bp){
        for (String currentId : tagIds){
            jdbcTemplate.update(SQL_INSERT_INTO_BLOGS_TAGS,
                    bp.getId(),
                    currentId);
        }
    }
    
    @Override
    public void removeTagsFromDB(BlogPost bp){
        jdbcTemplate.update(SQL_DELETE_TAGS_RELATIONSHIP, bp.getId());
    }
    
    //==========================================================================
    //                          HELPER METHODS
    //==========================================================================
    @Override
    public BlogPost appointUserToBlog(BlogPost blog) {
         try {
            User user =  jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_BLOG, 
                                               new UserMapper(), 
                                               blog.getId());
            
            blog.setUser(user);
            return blog;
            
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    
     
     
     
     
     
     
     
     
}
