package com.phonestoreweb.phonestore.service;

import com.phonestoreweb.phonestore.models.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories(Pageable pageable);

    Category findById(Long id);

    Category saveCategory(Category category);

    void deleteCategories(long[] ids);

    void deleteOneCategory(long id);

    int totalItem();
}
