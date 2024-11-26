package com.phonestoreweb.phonestore.controllers.admin.api;

import com.phonestoreweb.phonestore.models.Product;
import com.phonestoreweb.phonestore.service.ICategoryService;
import com.phonestoreweb.phonestore.service.IProductService;
import com.phonestoreweb.phonestore.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("admin/api/v1/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISupplierService supplierService;

    @GetMapping
    public String getProduct(Model model,
                             @RequestParam ("limit") int limit,
                             @RequestParam ("page")  int page,
                            @RequestParam("message") String message){
        Pageable pageable = PageRequest.of(page-1,limit);
        List<Product> products = productService.getAllProduct(pageable);
        model.addAttribute("products",products);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        model.addAttribute("totalPages",(int) Math.ceil((double)productService.totalItem()/limit));
        model.addAttribute("categories",categoryService.getCategoryNoPageable());
        model.addAttribute("suppliers",supplierService.getSupplierNoPageable());
        model.addAttribute("message",message);
        return "admin/product";
    }

    @GetMapping(value = "/findById/{id}")
    @ResponseBody
    public Product findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }

    @PostMapping
    @ResponseBody
    public Product addProduct(@RequestBody Product product){
       return productService.saveProduct(product);
    }
    @RequestMapping (value = "/image/{id}",method = {RequestMethod.POST, RequestMethod.GET})
    public String updateProductImage(@PathVariable Long id,@RequestParam ("page")  int page,
                               @RequestParam ("image") MultipartFile imageFile) throws Exception {
        Product product = productService.updateProductImage(id,imageFile);
        return "redirect:/admin/api/v1/product?limit=4&message=&page="+page;
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id){
        product.setId(id);
       return productService.saveProduct(product);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public List<Product> deleteOneProduct(@PathVariable long id){
        productService.deleteOneProduct(id);
        return productService.getAllProduct(PageRequest.of(0,4));
    }


}
