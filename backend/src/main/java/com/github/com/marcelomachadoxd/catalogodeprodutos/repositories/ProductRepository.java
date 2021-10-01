package com.github.com.marcelomachadoxd.catalogodeprodutos.repositories;

import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Category;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cats " +
            "WHERE (:category IS NULL OR :category IN cats )" +
            "AND ( UPPER(obj.name) LIKE UPPER(CONCAT('%',:name,'%') )  )")
    Page<Product> find(Category category, String name, Pageable pageable);

}
