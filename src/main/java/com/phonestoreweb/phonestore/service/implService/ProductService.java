package com.phonestoreweb.phonestore.service.implService;

import com.phonestoreweb.phonestore.models.Product;
import com.phonestoreweb.phonestore.repositories.ProductRepository;
import com.phonestoreweb.phonestore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }


    @Override
    public void deleteProduct(long[] ids) {
        for (long item:ids){
        productRepository.deleteById(item);
        }
    }
}
