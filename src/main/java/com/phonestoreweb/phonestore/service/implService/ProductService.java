package com.phonestoreweb.phonestore.service.implService;

import com.phonestoreweb.phonestore.dto.response.CloudinaryResponse;
import com.phonestoreweb.phonestore.models.Category;
import com.phonestoreweb.phonestore.models.Product;
import com.phonestoreweb.phonestore.repositories.ProductRepository;
import com.phonestoreweb.phonestore.service.IProductService;
import com.phonestoreweb.phonestore.untils.FileUploadUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UploadImageService uploadImageService;

    @Override
 //   @PreAuthorize("hasRole('ADMIN')")
    public List<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Product> getProductNoPageable() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
 //   @PreAuthorize("hasRole('ADMIN')")
    public Product saveProduct(Product product) {
       return productRepository.save(product);
    }


    @Override
//    @PreAuthorize("hasRole('ADMIN')")
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
//    @PreAuthorize("hasRole('ADMIN')")
    public Product updateProductImage(Long id, MultipartFile imageFile) throws Exception {
        Product product = productRepository.findById(id).orElse(null);
        FileUploadUntil.assertAllowed(imageFile,FileUploadUntil.IMAGE_PATTERN);
        String fileName = FileUploadUntil.getFileName(imageFile.getOriginalFilename());
        CloudinaryResponse response = this.uploadImageService.uploadImage(imageFile,fileName);
        product.setImage(response.getUrl());
        return productRepository.save(product);

    }

    @Override
    public List<Product> getProductByCategory(long categoryId) {
        return productRepository.getProductByCategory(categoryId);
    }

    @Override
    public List<Product> getProductByKeyword(String keyword, Pageable pageable) {
        return productRepository.getProductByKeyword(keyword,pageable);
    }


}
