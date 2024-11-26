package com.phonestoreweb.phonestore.service.implService;

import com.phonestoreweb.phonestore.models.Category;
import com.phonestoreweb.phonestore.repositories.CategoryRepository;
import com.phonestoreweb.phonestore.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Category> getCategoryNoPageable() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category saveCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void deleteCategories(long[] ids) {
        for (long item: ids){
            categoryRepository.deleteById(item);
        }
    }

    @Override
    public void deleteOneCategory(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public int totalItem() {
        return (int) categoryRepository.count();
    }

    @Override
    public Category findByCode(String category) {
        return categoryRepository.findByName(category);
    }


}
