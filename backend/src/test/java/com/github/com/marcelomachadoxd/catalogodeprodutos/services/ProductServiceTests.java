package com.github.com.marcelomachadoxd.catalogodeprodutos.services;

import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.ProductDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.Factory;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Category;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Product;
import com.github.com.marcelomachadoxd.catalogodeprodutos.repositories.CategoryRepository;
import com.github.com.marcelomachadoxd.catalogodeprodutos.repositories.ProductRepository;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions.DatabaseException;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;


    private Long existingId, notExistId, dependentId;
    private PageImpl<Product> page;
    private Product product;
    private ProductDTO productDTO;
    private Category category;


    @BeforeEach
    void SetUp() throws Exception {
        existingId = 1L;
        notExistId = 90000000L;
        dependentId = 4L;
        product = Factory.createProduct();
        page = new PageImpl<>(List.of(product));
        category = Factory.createCategory();
        productDTO = Factory.createProductDTO();

        Mockito.when(productRepository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);

        Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);

        Mockito.when(productRepository.findById(existingId)).thenReturn(Optional.of(product));

        Mockito.when(productRepository.findById(notExistId)).thenReturn(Optional.empty());

        Mockito.when(productRepository.getOne(existingId)).thenReturn(product);

        Mockito.when(productRepository.getOne(notExistId)).thenThrow(ResourceNotFoundException.class);


        Mockito.when(categoryRepository.getOne(existingId)).thenReturn(category);

        Mockito.when(categoryRepository.getOne(notExistId)).thenThrow(ResourceNotFoundException.class);


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

    @Test
    public void FindAllPagedShouldReturnPage(){

        Pageable pageable = PageRequest.of(0, 10);

        Page<ProductDTO> result = productService.findAllPaged(pageable);

        Assertions.assertNotNull(result);

        Mockito.verify(productRepository, Mockito.times(1)).findAll(pageable);
    }

    @Test
    public void FindByIdShouldTrowResourceNotFoundExceptionWhenIdNotExists(){

        Assertions.assertThrows(ResourceNotFoundException.class,() ->{

            productService.findById(notExistId);

        });

        Mockito.verify(productRepository, Mockito.times(1)).findById(notExistId);

    }

    @Test
    public void FindByIdShouldReturnNullOptionalWhenIdNotExists(){

        productDTO = productService.findById(existingId);

        Assertions.assertNotNull(productDTO);

        Mockito.verify(productRepository, Mockito.times(1)).findById(existingId);

    }

    @Test
    public void FindByIdShouldReturnSameIdAsProductDTOWhenIdExists(){

        productDTO = productService.findById(existingId);


        Assertions.assertNotNull(productDTO);

        Assertions.assertEquals(productDTO.getId(), existingId);

        Mockito.verify(productRepository, Mockito.times(1)).findById(existingId);

    }


    @Test
    public void UpdateShouldTrowResourceNotFoundExceptionWhenIdNotExists(){

        Assertions.assertThrows(ResourceNotFoundException.class, () ->{

            productRepository.getOne(notExistId);

        });

        Mockito.verify(productRepository, Mockito.times(1)).getOne(notExistId);

    }

    @Test
    public void UpdateShouldReturnProductDTOWhenIdExists(){

        productDTO = Factory.createProductDTO();

        ProductDTO result = productService.update(existingId, productDTO);

        Assertions.assertNotNull(result);


    }

}
