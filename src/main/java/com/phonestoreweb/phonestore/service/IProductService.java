package com.phonestoreweb.phonestore.service;

import com.phonestoreweb.phonestore.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    List<Product> getAllProduct(Pageable pageable);

    Product findProductById(Long id);

    Product saveProduct(Product product);

    void deleteProducts(long[] ids);

    void deleteOneProduct(long id);

    int totalItem();

    Product updateProductImage(Long id, String image) ;
}
