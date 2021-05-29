package com.github.com.marcelomachadoxd.catalogodeprodutos.repositories;

import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void DeleteShouldDeleteObjectWhenIdExists() {
        //Arrange
        Long existingId = 1L;

        //Act
        productRepository.deleteById(existingId);

        //Assert

        Optional<Product> result = productRepository.findById(existingId);

        Assertions.assertFalse(result.isPresent());


    }




}
