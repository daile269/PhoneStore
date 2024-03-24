package com.phonestoreweb.phonestore.repositories;

import com.phonestoreweb.phonestore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
