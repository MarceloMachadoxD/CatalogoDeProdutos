package com.github.com.marcelomachadoxd.catalogodeprodutos.services;

import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Category;
import com.github.com.marcelomachadoxd.catalogodeprodutos.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


}
