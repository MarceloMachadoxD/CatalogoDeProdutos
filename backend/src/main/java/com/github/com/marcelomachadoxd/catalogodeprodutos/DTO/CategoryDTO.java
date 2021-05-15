package com.github.com.marcelomachadoxd.catalogodeprodutos.DTO;

import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    Long id;
    String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public CategoryDTO(Category entity){
        this.id = entity.getId();
        this.name = entity.getName();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
