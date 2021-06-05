package com.github.com.marcelomachadoxd.catalogodeprodutos;

import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.ProductDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Category;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct() {
        Product product = new Product(1L, "Phone Mock", "Mock", 111.11, "http://mock", Instant.parse("2020-07-13T20:50:07.12345Z"));
        product.getCategories().add(createCategory());
        return product;
    }

    public static ProductDTO createProductDTO(){
        Product product = createProduct();

        return new ProductDTO(product, product.getCategories());
    }

    public static Category createCategory(){
        return new Category(2L, "Electronics");
    }
}
