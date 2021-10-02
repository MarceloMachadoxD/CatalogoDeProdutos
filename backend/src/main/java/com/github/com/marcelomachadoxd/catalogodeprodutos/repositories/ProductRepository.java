package com.github.com.marcelomachadoxd.catalogodeprodutos.repositories;

import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Category;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cats " +
            "WHERE ( COALESCE(:categories) IS NULL OR cats IN :categories )" +
            "AND ( UPPER(obj.name) LIKE UPPER(CONCAT('%',:name,'%') )  )")
    Page<Product> find(List<Category> categories, String name, Pageable pageable);

    @Query("SELECT obj FROM Product obj JOIN FETCH obj.categories WHERE obj in :products") //fix n+1
    List<Product> findProductsWithCategories(List<Product> products);

}