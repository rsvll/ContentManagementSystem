/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.controller;

import com.sg.blogcms.dto.BlogPost;
import com.sg.blogcms.dto.Category;
import com.sg.blogcms.dto.StaticPage;
import com.sg.blogcms.dto.Tags;
import com.sg.blogcms.dto.User;
import com.sg.blogcms.service.BlogsCMSService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author josesosa
 */

@Controller
public class BlogsCMSController {
    BlogsCMSService blogsService;
    
    
    
    @Inject
    public BlogsCMSController(BlogsCMSService blogsService) {
        this.blogsService = blogsService;
        
    }
    
    @RequestMapping(value = {"/", "index", ""}, method = RequestMethod.GET)
    public String landingPage(HttpServletRequest request, Model model) {
        List<BlogPost> lastTenBlogs;
        lastTenBlogs = blogsService.selectLastTenBlogs();
        blogsService.updateListOfBlogs(lastTenBlogs);
        model.addAttribute("lastTenBlogs", lastTenBlogs);
        return "index";
    }

//======================= FOR ALL BLOGS ==================================================

    @RequestMapping(value= {"/blogs"}, method = RequestMethod.GET)
    public String blogsPage(HttpServletRequest request, Model model) {
        List<BlogPost> allBlogs;
        allBlogs = blogsService.selectAllBlogs();
        allBlogs = allBlogs
                .stream()
                .filter(s -> s.getIsApproved() == true)
                .collect(Collectors.toList());
        model.addAttribute("allBlogs", allBlogs);
        return "blogs";
    }
    
    @RequestMapping(value= {"/unapprovedBlogs"}, method = RequestMethod.GET)
    public String unApprovedBlogs(HttpServletRequest request, Model model) {
        List<BlogPost> allBlogs;
        allBlogs = blogsService.selectAllBlogs();
        allBlogs = allBlogs
                .stream()
                .filter(s -> s.getIsApproved() == false)
                .collect(Collectors.toList());
        List<StaticPage> inactivePages = blogsService.selectAllInactiveStaticPages();
        
        model.addAttribute("inactivePages",inactivePages);
        model.addAttribute("allBlogs", allBlogs);
        return "unapprovedBlogs";
    }
    
    @RequestMapping(value = "/deleteBlogPost", method = RequestMethod.GET)
    public String deleteBlogPost(HttpServletRequest request, Model model) {
        int bpId = Integer.parseInt(request.getParameter("blogId"));
        blogsService.removeBlog(bpId);
        return "redirect:blogs";
    }
    
    @RequestMapping(value = "/approveBlog", method = RequestMethod.GET)
    public String approveBlog(HttpServletRequest request, Model model) {
        int bpId = Integer.parseInt(request.getParameter("blogId"));
        blogsService.approveBlog(bpId);
        return "redirect:blogs";
    }
    
    @RequestMapping(value= {"/displayCreateBlogPostPage"}, method = RequestMethod.GET)
    public String displayCreateBlogPostPage(HttpServletRequest request,Model model) {
            List<Category> allCategories = blogsService.selectAllCategories();
            List<Tags> allTags = blogsService.selectAllTags();
            model.addAttribute("allCategories",allCategories);
            model.addAttribute("allTags",allTags);
        return "createBlogPost";
    }
    
    @RequestMapping(value = "/createBlogPost", method = RequestMethod.POST)
    public String createBlogPost(HttpServletRequest request ,Model model) {
        BlogPost bp = new BlogPost();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String username = request.getParameter("username");
        User user = blogsService.selectUserByUsername(username);
        
        
	Date date = new Date();
	Date date2 = new Date("2099/12/31 12:00:00");
        bp.setTitle(request.getParameter("title"));
        bp.setDescription(request.getParameter("description"));
        bp.setContent(request.getParameter("content"));
        bp.setAuthor(username);
        bp.setCreatedDate(date);
        bp.setPublishDate(date);
        bp.setExpirationDate(date2);
        
        if(user.getIsAdmin()){ 
            bp.setIsApproved(true);
        }else{
            bp.setIsApproved(false);
        }
        
        bp.setUserId(user.getUserId());
        bp.setCatId(1);
        
        String[] tagIds = request.getParameterValues("tags");
        
        blogsService.createBlog(bp);
        
        blogsService.updateBlogAndTag(tagIds, bp);
        
        List<BlogPost> allBlogs;
        allBlogs = blogsService.selectAllBlogs();
        model.addAttribute("allBlogs", allBlogs);
        
        return "blogs";
    }
    
    @RequestMapping(value="/displayBlog/{blogId}", method = RequestMethod.GET)
    public String displayBlog(HttpServletRequest request, Model model,@PathVariable int blogId){
        BlogPost bp = blogsService.selectBlog(blogId);
        List<Tags> tagList = blogsService.selectTagByBlogId(blogId);
        model.addAttribute("bp",bp);
        
        return "blogpost";
    }
            
    @RequestMapping(value = "/chooseBlogPostToUpdate", method = RequestMethod.GET)
    public String chooseBlogPostToUpdate(HttpServletRequest request, Model model) {
        int blogId = Integer.parseInt(request.getParameter("blogId"));
        BlogPost bp = blogsService.selectBlog(blogId);
        model.addAttribute("bp", bp);
        return "editPage";
    }
    
    @RequestMapping(value = "/updateBlogPost", method = RequestMethod.GET)
    public String updateBlogPost(HttpServletRequest request, Model model) {
        
            int blogId = Integer.parseInt(request.getParameter("bpId"));
            BlogPost bp = blogsService.selectBlog(blogId);
            blogsService.removeTagsFromDB(bp);
            
            String username = request.getParameter("username");
            User user = blogsService.selectUserByUsername(username);

            bp.setTitle(request.getParameter("title"));
            bp.setDescription(request.getParameter("description"));
            bp.setContent(request.getParameter("content"));
            bp.setAuthor(username);

            
            bp.setUserId(user.getUserId());
            //bp.setCatId(Integer.parseInt(request.getParameter("catId")));
            int catId = Integer.parseInt(request.getParameter("cats"));
            bp.setCatId(catId);
            
            String[] tagIds = request.getParameterValues("tags");
            blogsService.updateBlogAndTag(tagIds, bp);
            
            blogsService.updateBlog(bp);
        
        
        return "redirect:blogs";
    }
   
    
}
