package com.github.com.marcelomachadoxd.catalogodeprodutos.repositories;

import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
