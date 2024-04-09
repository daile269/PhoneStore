package com.phonestoreweb.phonestore.controllers.admin.api;

import com.phonestoreweb.phonestore.models.Category;
import com.phonestoreweb.phonestore.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin/api/v1/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;



    @GetMapping()
    public String getAllCategoriesPagination(@RequestParam("page") int page,
                                             @RequestParam ("limit") int limit,
                                             @RequestParam("message") String message,
                                             Model model){

        Pageable pageable = PageRequest.of(page-1,limit);
        List<Category> categories = categoryService.getAllCategories(pageable);
        model.addAttribute("categories",categories);
        model.addAttribute("limit",limit);
        model.addAttribute("totalPages",(int) Math.ceil( (double) (categoryService.totalItem())/limit));

        model.addAttribute("page",page);

        model.addAttribute("message",message);

        return "admin/category";
    }


    @GetMapping(value = "/findById/{id}")
    @ResponseBody
    public Category findCategoryById(@PathVariable("id") Long id){
        return categoryService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public Category addCategory(@RequestBody Category category){
        return  categoryService.saveCategory(category);
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public Category updateCategory(@RequestBody Category category, @PathVariable Long id){
        category.setId(id);
        return categoryService.saveCategory(category);
    }

/*    @DeleteMapping()
    public String deleteCategories(@RequestBody long[] ids){
        categoryService.deleteCategories(ids);
        return "redirect:/admin/api/v1/category";
    }*/
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public List<Category> deleteOneCategory(@PathVariable Long id){
        categoryService.deleteOneCategory(id);
        return categoryService.getCategoryNoPageable();
    }

}
