package com.github.com.marcelomachadoxd.catalogodeprodutos.services;

import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.ProductDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.repositories.ProductRepository;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ProductServiceIT {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    private Long existingId;
    private Long notExistingId;
    private Long countTOtalProducts;



    @BeforeEach
    void SetUp() {

        existingId = 1L;
        notExistingId = 900000L;
        countTOtalProducts = 25L;
    }


    @Test
    public void deleteShouldDeleteResourceWhenIdExists(){

        productService.deleteProductById(existingId);

        Assertions.assertEquals(countTOtalProducts -1, productRepository.count());
        Assertions.assertFalse(productRepository.existsById(existingId));
    }

    @Test
    public void deleteShouldTrowResourceNotFoundExceptionWhenIdNotExists(){

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            productService.deleteProductById(notExistingId);
        });
    }

    @Test
    public void FindAllPagedShouldReturnPagedWhenPage0Size10(){
        PageRequest pageRequest = PageRequest.of(0,10);

        Page<ProductDTO> result = productService.findAllPaged(pageRequest);

        Assertions.assertFalse(result.isEmpty());

        Assertions.assertEquals(0, result.getNumber());
        Assertions.assertEquals(10, result.getSize());
        Assertions.assertEquals(countTOtalProducts, result.getTotalElements());

    }

    @Test
    public void FindAllPagedShouldReturnEmptyWhenPage999Size10(){
        PageRequest pageRequest = PageRequest.of(999,10);

        Page<ProductDTO> result = productService.findAllPaged(pageRequest);

        Assertions.assertTrue(result.isEmpty());

    }



}
