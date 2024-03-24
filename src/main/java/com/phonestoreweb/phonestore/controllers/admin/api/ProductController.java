package com.phonestoreweb.phonestore.controllers.admin.api;

import com.phonestoreweb.phonestore.models.Product;
import com.phonestoreweb.phonestore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin/api/v1/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public String getProduct(Model model){
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products",products);
        return "admin/product";
    }

    @GetMapping(value = "/{id}")
    public Product findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }

    @PostMapping
    public String addProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return  "redirect:/admin/product";
    }

    @PutMapping(value = "/{id}")
    public String updateProduct(@RequestBody Product product, @PathVariable Long id){
        product.setId(id);
        productService.saveProduct(product);
        return  "redirect:/admin/product";
    }

    @DeleteMapping
    public String deleteProduct(long[] ids){
        productService.deleteProduct(ids);
        return  "redirect:/admin/product";
    }


}
