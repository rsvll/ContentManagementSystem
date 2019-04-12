/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blogcms.controller;


import com.sg.blogcms.dto.Category;
import com.sg.blogcms.service.CategoryCMSService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author josesosa
 */
@Controller
public class CategoryCMSController {
    CategoryCMSService catService;
    
    @Inject
    public CategoryCMSController(CategoryCMSService catService) {
        this.catService = catService;
        
    }

    @RequestMapping(value= {"/categories"}, method = RequestMethod.GET)
    public String displayAllCategories(HttpServletRequest request, Model model) {
        List<Category> allCategories;
        allCategories = catService.selectAllCategories();
        model.addAttribute("allCategories", allCategories);
        
        return "categories";
    }
    
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
    public String deleteCategory(HttpServletRequest request, Model model) {
        int catID = Integer.parseInt(request.getParameter("categoryId"));
        catService.removeCategory(catID);
        return "redirect:categories";
    }
    
    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public String createCategory(HttpServletRequest request, Model model) {
        Category cat = new Category();
        cat.setCatName(request.getParameter("categoryName"));
        cat.setDescription(request.getParameter("categoryDescription"));
        catService.createCategory(cat);
        return "redirect:categories";
    }
    
    @RequestMapping(value = "/chooseCategoryToUpdate", method = RequestMethod.GET)
    public String chooseCategoryToUpdate(HttpServletRequest request, Model model) {
        int catID = Integer.parseInt(request.getParameter("categoryId"));
        String viewType = request.getParameter("viewType");
        model.addAttribute("viewType",viewType);
        Category cat = catService.selectCatById(catID);
        model.addAttribute("cat",cat);
        model.addAttribute("catID",catID);
        List<Category> allCategories;
        allCategories = catService.selectAllCategories();
        model.addAttribute("allCategories", allCategories);
        return "categories";
    }
    
    @RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
    public String updateCategory(HttpServletRequest request, Model model) {
        
            int catID = Integer.parseInt(request.getParameter("categoryId"));
            Category cat = catService.selectCatById(catID);
            //model.addAttribute("catID", catID);
            cat.setCatName(request.getParameter("categoryName"));
            cat.setDescription(request.getParameter("categoryDescription"));    
            catService.updateCategory(cat);
        
        
        return "redirect:categories";
    }
    
}
