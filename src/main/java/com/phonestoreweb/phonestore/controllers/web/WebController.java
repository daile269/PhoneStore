package com.phonestoreweb.phonestore.controllers.web;

import com.phonestoreweb.phonestore.models.Category;
import com.phonestoreweb.phonestore.models.Product;
import com.phonestoreweb.phonestore.service.ICategoryService;
import com.phonestoreweb.phonestore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/","/home"})
public class WebController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;



    /*@GetMapping
    public String getProduct(Model model,
                             @RequestParam("limit") int limit,
                             @RequestParam ("page")  int page){
        Pageable pageable = PageRequest.of(page-1,limit);
        List<Product> products = productService.getAllProduct(pageable);
        model.addAttribute("products",products);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        model.addAttribute("totalPages",(int) Math.ceil((double)productService.totalItem()/limit));
        model.addAttribute("categories",categoryService.getCategoryNoPageable());
        model.addAttribute("suppliers",supplierService.getSupplierNoPageable());
        return "web/index";
    }*/
    @GetMapping(value = {"","/products"})
    public String showIndex(Model model, @RequestParam(value = "category",defaultValue = "") String category){
        model.addAttribute("categories",categoryService.getCategoryNoPageable());
        if(!category.equals("")){
            Category cate = categoryService.findByCode(category);
            model.addAttribute("productsWithCategory",productService.getProductByCategory(cate.getId()));
            model.addAttribute("category",cate);
        }
        else {
            model.addAttribute("productsWithCategory",productService.getProductNoPageable());
            model.addAttribute("category",null);
        }

        return "web/index";
    }

    @GetMapping(value = "/category/{id}")
    @ResponseBody
    public Category findCategoryById(@PathVariable("id") Long id){
        return categoryService.findById(id);
    }

    @GetMapping(value = "/product/{id}")
    public String findProductById(@PathVariable("id") Long id,Model model){
        Product product = productService.findProductById(id);
        model.addAttribute("product",product);
        Category category = categoryService.findById(product.getCategoryId());
        model.addAttribute("category",category);
        model.addAttribute("productsWithCategory",productService.getProductByCategory(product.getCategoryId()));
        model.addAttribute("categories",categoryService.getCategoryNoPageable());
        return "web/view_product";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "admin/login";
    }
    @GetMapping("/register")
    public String registerForm(){
        return "admin/register";
    }
}
