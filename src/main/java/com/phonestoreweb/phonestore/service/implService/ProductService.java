package com.phonestoreweb.phonestore.service.implService;

import com.phonestoreweb.phonestore.models.Product;
import com.phonestoreweb.phonestore.repositories.ProductRepository;
import com.phonestoreweb.phonestore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
       return productRepository.save(product);
    }


    @Override
    public void deleteProducts(long[] ids) {
        for (long item:ids){
        productRepository.deleteById(item);
        }
    }

    @Override
    public void deleteOneProduct(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public int totalItem() {
        return (int) productRepository.count();
    }

    @Override
    public Product updateProductImage(Long id, String image) {
        Product product = productRepository.findById(id).orElse(null);
        product.setImage(image);
        return productRepository.save(product);
    }
}
