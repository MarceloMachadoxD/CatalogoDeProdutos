package com.github.com.marcelomachadoxd.catalogodeprodutos.services;

import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.CategoryDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Category;
import com.github.com.marcelomachadoxd.catalogodeprodutos.repositories.CategoryRepository;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = categoryRepository.findAll();

        List<CategoryDTO> listDto = list.stream().map(CategoryDTO::new).collect(Collectors.toList());

        return listDto;
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> obj = categoryRepository.findById(id);
        Category category = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
        return new CategoryDTO(category);
    }


}
