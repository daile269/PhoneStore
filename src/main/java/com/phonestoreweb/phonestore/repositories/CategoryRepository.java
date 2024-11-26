package com.phonestoreweb.phonestore.repositories;

import com.phonestoreweb.phonestore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query(value = "select * from category where code=?",nativeQuery = true)
    Category findByName(String category);
}
