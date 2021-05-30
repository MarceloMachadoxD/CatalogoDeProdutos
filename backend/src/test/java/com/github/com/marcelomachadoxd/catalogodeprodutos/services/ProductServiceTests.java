package com.github.com.marcelomachadoxd.catalogodeprodutos.services;

import com.github.com.marcelomachadoxd.catalogodeprodutos.repositories.ProductRepository;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions.DatabaseException;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


    private Long existingId, notExistId, dependentId;


    @BeforeEach
    void SetUp() throws Exception {
        existingId = 1L;
        notExistId = 90000000L;
        dependentId = 4L;

        Mockito.doNothing().when(productRepository).deleteById(existingId);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(productRepository).deleteById(notExistId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(productRepository).deleteById(dependentId);

    }


    @Test
    public void DeleteShouldDoNothingWhenIdExists(){

        Assertions.assertDoesNotThrow(() -> {
            productService.deleteProductById(existingId);

        });
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    public void DeleteShouldTrowEmptyResultDataAccessExceptionWhenIdNotExists(){

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            productService.deleteProductById(notExistId);

        });
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(notExistId);
    }

    @Test
    public void DeleteShouldTrowDatabaseExceptionWhenIdIsDepend(){

        Assertions.assertThrows(DatabaseException.class, () -> {
            productService.deleteProductById(dependentId);

        });
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(dependentId);
    }


}
