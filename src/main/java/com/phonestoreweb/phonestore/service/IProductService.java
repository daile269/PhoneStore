package com.phonestoreweb.phonestore.service;

import com.phonestoreweb.phonestore.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct();

    Product findProductById(Long id);

    void saveProduct(Product product);

    void deleteProduct(long[] id);
}
