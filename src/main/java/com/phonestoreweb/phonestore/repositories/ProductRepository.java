package com.phonestoreweb.phonestore.repositories;

import com.phonestoreweb.phonestore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT *,(price*(100-discount)/100)AS currentPrice FROM product where category_id =:categoryId", nativeQuery = true)
    List<Product> getProductByCategory(long categoryId);



}
