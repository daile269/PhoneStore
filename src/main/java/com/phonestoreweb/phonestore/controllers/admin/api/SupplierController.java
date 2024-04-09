package com.phonestoreweb.phonestore.controllers.admin.api;

import com.phonestoreweb.phonestore.models.Supplier;
import com.phonestoreweb.phonestore.models.Supplier;
import com.phonestoreweb.phonestore.service.ISupplierService;
import com.phonestoreweb.phonestore.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin/api/v1/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping
    public String getSuppliers(Model model,
                               @RequestParam ("page") int page,
                               @RequestParam ("limit")int limit,
                               @RequestParam("message") String message){
        Pageable pageable = PageRequest.of(page-1,limit);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        model.addAttribute("totalPages", (int) Math.ceil((double) supplierService.totalItem()/limit));
        model.addAttribute("suppliers",supplierService.getAllSuppliers(pageable));
        model.addAttribute("message",message);
        return "admin/supplier";
    }

    @GetMapping(value = "/findById/{id}")
    @ResponseBody
    public Supplier findSupplierById(@PathVariable Long id){
        return supplierService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public Supplier addSupplier(@RequestBody Supplier Supplier){
        return supplierService.saveSupplier(Supplier);
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public Supplier updateSupplier(@RequestBody Supplier supplier, @PathVariable Long id){
        supplier.setId(id);
        return supplierService.saveSupplier(supplier);

    }

//    @DeleteMapping
//    @ResponseBody
//    public List<Supplier> deleteSuppliers(long[] ids){
//        supplierService.deleteSupplier(ids);
//        return supplierService.getAllSuppliers(PageRequest.of(1,3));
//    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public List<Supplier> deleteSupplier(@PathVariable Long id){
        supplierService.deleteSupplier(id);
        return supplierService.getSupplierNoPageable();
    }

}
