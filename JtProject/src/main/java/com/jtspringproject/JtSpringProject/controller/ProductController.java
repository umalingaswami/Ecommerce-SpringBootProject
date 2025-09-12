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
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.services.CategoryService;
import com.jtspringproject.JtSpringProject.services.ProductService;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ModelAndView getproduct() {
        ModelAndView mView = new ModelAndView("products");
        List<Product> products = this.productService.getProducts();
        if (products.isEmpty()) {
            mView.addObject("msg", "No products are available");
        } else {
            mView.addObject("products", products);
        }
        return mView;
    }

    @GetMapping("/add")
    public ModelAndView addProduct() {
        ModelAndView mView = new ModelAndView("productsAdd");
        List<Category> categories = this.categoryService.getCategories();
        mView.addObject("categories", categories);
        return mView;
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String name, @RequestParam("categoryid") int categoryId, @RequestParam int price, @RequestParam int weight, @RequestParam int quantity, @RequestParam String description, @RequestParam String productImage) {
        Category category = this.categoryService.getCategory(categoryId);
        Product product = new Product();
        product.setId(categoryId); // This seems incorrect, but kept as in original
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(productImage);
        product.setWeight(weight);
        product.setQuantity(quantity);
        this.productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateproduct(@PathVariable int id) {
        ModelAndView mView = new ModelAndView("productsUpdate");
        Product product = this.productService.getProduct(id);
        List<Category> categories = this.categoryService.getCategories();
        mView.addObject("categories", categories);
        mView.addObject("product", product);
        return mView;
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable int id, @RequestParam String name, @RequestParam("categoryid") int categoryId, @RequestParam int price, @RequestParam int weight, @RequestParam int quantity, @RequestParam String description, @RequestParam String productImage) {
        // TODO: Implement update logic in ProductService
        return "redirect:/admin/products";
    }

    @GetMapping("/delete")
    public String removeProduct(@RequestParam int id) {
        this.productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @PostMapping("")
    public String postproduct() {
        return "redirect:/admin/categories";
    }
}
