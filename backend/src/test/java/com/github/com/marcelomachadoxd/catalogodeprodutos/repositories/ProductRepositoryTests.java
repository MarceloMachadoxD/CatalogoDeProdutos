package com.github.com.marcelomachadoxd.catalogodeprodutos.repositories;

import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    private Long existingId;
    private Long notExistId;

    @BeforeEach
    void SetUp() throws Exception{
         existingId = 1L;
         notExistId = 90000000L;
    }

    @Test
    public void DeleteShouldDeleteObjectWhenIdExists() {
        //Arrange


        //Act
        productRepository.deleteById(existingId);

        //Assert

        Optional<Product> result = productRepository.findById(existingId);

        Assertions.assertFalse(result.isPresent());


    }

    @Test
    public void DeleteShouldTrowExceptionEmptyResultDataAccessExceptionWhenIdNotExists() {
        //Arrange

        //Assertion
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {

            //Act
            productRepository.deleteById(notExistId);

        });
    }


}
