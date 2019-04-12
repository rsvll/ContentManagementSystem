/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.controller;

import com.sg.blogcms.dto.StaticPage;
import com.sg.blogcms.dto.User;
import com.sg.blogcms.service.StaticPageCMSService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author svlln
 */
@Controller
public class StaticPageCMSController {
    StaticPageCMSService spService;
    
    
    @Inject
    public StaticPageCMSController(StaticPageCMSService spService) {
        this.spService = spService;
        
    }
    
    @RequestMapping(value = {"/viewAllStaticPages"}, method = RequestMethod.GET)
    public String viewAllStaticPage(HttpServletRequest request, Model model) {
        
        List<StaticPage> sp = spService.selectAllStaticPages();
        int calc = calculateNearestNthMultiple(sp.size(),4);
        model.addAttribute("sp",sp);
        model.addAttribute("calc",calc);
        return "allStaticPages";
    }
    
    @RequestMapping(value="/displayStaticPage/{spId}", method = RequestMethod.GET)
    public String displayStaticPage(HttpServletRequest request, Model model,@PathVariable int spId){
        StaticPage sp = spService.selectStaticPage(spId);
        model.addAttribute("sp",sp);
        
        return "staticpages";
    }
    
    @RequestMapping(value = "/chooseStaticPageToUpdate", method = RequestMethod.GET)
    public String chooseStaticPageToUpdate(HttpServletRequest request, Model model) {
        int spId = Integer.parseInt(request.getParameter("spId"));
        StaticPage sp = spService.selectStaticPage(spId);
        model.addAttribute("sp", sp);
        return "editPage";
    }
    
    @RequestMapping(value = "/updateStaticPage", method = RequestMethod.GET)
    public String updateStaticPage(HttpServletRequest request, Model model) {
        
            int spId = Integer.parseInt(request.getParameter("spId"));
            StaticPage sp = spService.selectStaticPage(spId);
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Date date = new Date();
            
            String username = request.getParameter("username");
            User user = spService.selectUserByUsername(username);

            sp.setTitle(request.getParameter("title"));
            sp.setDescription(request.getParameter("description"));
            sp.setContent(request.getParameter("content"));
            sp.setAuthor(username);
//            sp.setCreatedDate(date);
//            sp.setPublishDate(date);
//            sp.setExpirationDate(date);

            if (null != request.getParameter("isActive")) {
                sp.setIsActive(true);
            }

            sp.setUserId(user.getUserId());
            
            spService.updateStaticPage(sp);
            
        return "redirect:viewAllStaticPages";
    }
    
    @RequestMapping(value= {"/displayCreateStaticPagePage"}, method = RequestMethod.GET)
    public String displayCreateStaticPagePage(HttpServletRequest request,Model model) {
            
            
        return "createStaticPage";
    }
    
    @RequestMapping(value = "/createStaticPage", method = RequestMethod.POST)
    public String createStaticPage(HttpServletRequest request ,Model model) {
        StaticPage statpage = new StaticPage();
        
        String username = request.getParameter("username");
        User user = spService.selectUserByUsername(username);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
        Date date2 = new Date("2099/12/31 12:00:00");

	statpage.setTitle(request.getParameter("title"));
        statpage.setDescription(request.getParameter("description"));
        statpage.setContent(request.getParameter("content"));
        statpage.setAuthor(username);
        statpage.setCreatedDate(date);
        statpage.setPublishDate(date);
        statpage.setExpirationDate(date2);
        
        if (user.getIsAdmin()) {
                statpage.setIsActive(true);
        }
        
        statpage.setUserId(user.getUserId());
        
        spService.createStaticPage(statpage);
        
        List<StaticPage> sp = spService.selectAllStaticPages();
        int calc = calculateNearestNthMultiple(sp.size(),4);
        model.addAttribute("sp",sp);
        model.addAttribute("calc",calc);

        return "allStaticPages";
    }
    
    @RequestMapping(value = "/approveStaticPage", method = RequestMethod.GET)
    public String approveStaticPage(HttpServletRequest request, Model model) {
        int spId = Integer.parseInt(request.getParameter("spId"));
        spService.approveStaticPage(spId);
        return "redirect:viewAllStaticPages";
    }
    
    @RequestMapping(value = "/deleteStaticPage", method = RequestMethod.GET)
    public String deleteStaticPage(HttpServletRequest request, Model model) {
        int spId = Integer.parseInt(request.getParameter("spId"));
        spService.deleteStaticPage(spId);
        return "redirect:viewAllStaticPages";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public int calculateNearestNthMultiple(int number, int destination){
        
        int a = destination;
        while(number > destination ){
            
            a+=destination;
            number-=destination;
        }
        
        
        
        return a;
    }
    
//    @RequestMapping(value = {"/createBlogPost/{viewType2}"}, method = RequestMethod.GET)
//    public String StaticPageAddCategory(HttpServletRequest request, Model model,@PathVariable String viewType2) {
//        
//        List<Category> allCategories = spService.selectAllCategories();
//
//        model.addAttribute("viewType2",viewType2);
//        model.addAttribute("allCategories",allCategories);
//        return "createBlogPost";
//    }
//    
//    @RequestMapping(value = {"/createBlogPost/{viewType}"}, method = RequestMethod.GET)
//    public String StaticPageAddTag(HttpServletRequest request, Model model,@PathVariable String viewType) {
//        
//        List<Tags> allTags = spService.selectAllTags();
//        
//        
//        
//        
//        model.addAttribute("viewType",viewType);
//        model.addAttribute("allTags",allTags);
//        return "createBlogPost";
//    }
    
//    @RequestMapping(value = { "/","staticPage", ""}, method = RequestMethod.GET)
//    public String StaticPages(HttpServletRequest request, Model model){
//        List<StaticPage> staticPages;
//        staticPages = spService.selectAllStaticPages();
//        model.addAttribute("selectAllStaticPages", staticPages);
//        return "staticpages";
//    }
    
    
}
