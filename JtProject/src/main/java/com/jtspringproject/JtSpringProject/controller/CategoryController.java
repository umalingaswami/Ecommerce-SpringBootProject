package com.jtspringproject.JtSpringProject.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.services.CategoryService;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView getCategories() {
        ModelAndView mView = new ModelAndView("categories");
        List<Category> categories = this.categoryService.getCategories();
        mView.addObject("categories", categories);
        return mView;
    }

    @PostMapping
    public String addCategory(@RequestParam("categoryname") String category_name) {
        this.categoryService.addCategory(category_name);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete")
    public String removeCategory(@RequestParam int id) {
        this.categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/update")
    public String updateCategory(@RequestParam("categoryid") int id, @RequestParam String categoryname) {
        this.categoryService.updateCategory(id, categoryname);
        return "redirect:/admin/categories";
    }

    @GetMapping("/add")
    public String showAddCategoryForm() {
        return "categoryAdd";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditCategoryForm(@PathVariable int id) {
        Category category = categoryService.getCategory(id);
        ModelAndView mView = new ModelAndView("categoryEdit");
        mView.addObject("category", category);
        return mView;
    }

    @PostMapping("/edit/{id}")
    public String editCategory(@PathVariable int id, @RequestParam("categoryname") String categoryname) {
        categoryService.updateCategory(id, categoryname);
        return "redirect:/admin/categories";
    }
}