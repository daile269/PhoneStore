package com.phonestoreweb.phonestore.repositories;

import com.phonestoreweb.phonestore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
