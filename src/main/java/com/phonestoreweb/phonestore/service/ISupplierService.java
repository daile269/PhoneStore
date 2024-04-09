package com.phonestoreweb.phonestore.service;

import com.phonestoreweb.phonestore.models.Supplier;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISupplierService {
    List<Supplier> getAllSuppliers(Pageable pageable);

    List<Supplier> getSupplierNoPageable();
    Supplier saveSupplier(Supplier supplier);

    void deleteSupplier(long id);

    Supplier findById(long id);

    int totalItem();
}