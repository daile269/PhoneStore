package com.phonestoreweb.phonestore.service.implService;

import com.phonestoreweb.phonestore.models.Supplier;
import com.phonestoreweb.phonestore.repositories.SupplierRepository;
import com.phonestoreweb.phonestore.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public List<Supplier> getAllSuppliers(Pageable pageable) {
        return supplierRepository.findAll(pageable).getContent();
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public Supplier findById(long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public int totalItem() {
        return (int) supplierRepository.count();
    }
}
