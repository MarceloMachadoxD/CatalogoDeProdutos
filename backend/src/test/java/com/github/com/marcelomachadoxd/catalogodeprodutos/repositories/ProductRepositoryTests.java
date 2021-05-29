package com.github.com.marcelomachadoxd.catalogodeprodutos.repositories;

import com.github.com.marcelomachadoxd.catalogodeprodutos.Factory;
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

    private Long existingId, notExistId, countTotalProducts;


    @BeforeEach
    void SetUp() throws Exception {
        existingId = 1L;
        notExistId = 90000000L;
        countTotalProducts = 25L;
    }

    @Test
    public void InsertShouldPersistWithAutoIncrementWhenIdIsNull() {
        Product product = Factory.createProduct();
        product.setId(null);

        product = productRepository.save(product);

        Assertions.assertNotNull(product.getId());

        Assertions.assertEquals(product.getId(), countTotalProducts + 1);

    }

    @Test
    public void FindProductShouldNotReturnNullWhenIdExists() {
        Optional<Product> product = productRepository.findById(existingId);

        Assertions.assertTrue(product.isPresent());
    }

    @Test
    public void FindProductShouldReturnNullWhenIdExists() {
        Optional<Product> product = productRepository.findById(notExistId);

        Assertions.assertFalse(product.isPresent());

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
