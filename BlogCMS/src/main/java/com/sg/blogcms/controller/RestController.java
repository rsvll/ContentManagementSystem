/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.controller;

import com.sg.blogcms.dao.CategoryCMSDao;
import com.sg.blogcms.dao.TagsCMSDao;
import com.sg.blogcms.dto.Category;
import com.sg.blogcms.dto.Tags;
import com.sg.blogcms.service.CategoryCMSService;
import com.sg.blogcms.service.TagsCMSService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author josesosa
 */
@Controller
public class RestController {
     TagsCMSService tagService;
     CategoryCMSService catService;
     TagsCMSDao tagDao;
     CategoryCMSDao catDao;
    
     @Inject
    public RestController(TagsCMSService tagService, CategoryCMSService catService, TagsCMSDao tagDao, CategoryCMSDao catDao) {
        this.tagService = tagService;
        this.catService = catService;
        this.tagDao = tagDao;
        this.catDao = catDao;
    }

    
     
//******************************CATEGORIES ENDPOINTS**************************//
    @RequestMapping(value = "/obtainAllCategories", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getAllCategories() {
        return catService.selectAllCategories();
    }
    
    @RequestMapping(value = "/appendACategory", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Category appendACategory(@RequestBody Category cat) {
        return catService.createCategory(cat);
    }
     

//******************************TAGS ENDPOINTS********************************//    
    @RequestMapping(value = "/obtainAllTags", method = RequestMethod.GET)
    @ResponseBody
    public List<Tags> getAllTags() {
        return tagService.SelectAllTags();
    }
    
    @RequestMapping(value = "/appendATag", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Tags appendATag(@RequestBody Tags tag) {
        return tagService.createTag(tag);
    }
    
    
    
}
