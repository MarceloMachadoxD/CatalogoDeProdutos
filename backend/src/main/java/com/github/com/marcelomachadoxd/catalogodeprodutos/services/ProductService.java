package com.github.com.marcelomachadoxd.catalogodeprodutos.services;

import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.CategoryDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.ProductDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Category;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Product;
import com.github.com.marcelomachadoxd.catalogodeprodutos.repositories.CategoryRepository;
import com.github.com.marcelomachadoxd.catalogodeprodutos.repositories.ProductRepository;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions.DatabaseException;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
        Page<Product> list = productRepository.findAll(pageRequest);

        return list.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        try {
            Optional<Product> obj = productRepository.findById(id);
            Product product = obj.orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));

            return new ProductDTO(product, product.getCategories());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Not found id: " + id);

        }
    }

    @Transactional(readOnly = false)
    public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();

        copyDtoToEntity(dto, product);

/*      product.setName(dto.getName());
        product.setDate(dto.getDate());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgURL(dto.getImgURL());*/


        product = productRepository.save(product);
        return new ProductDTO(product);

    }


    @Transactional(readOnly = false)
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product product = productRepository.getOne(id);

            copyDtoToEntity(dto, product);

     /*       product.setName(dto.getName());
            product.setDate(dto.getDate());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setImgURL(dto.getImgURL());*/


            return new ProductDTO(product);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Not found id: " + id);

        }
    }


    public void deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Not found id: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation trying to delete " + id);
        }
    }


    private void copyDtoToEntity(ProductDTO dto, Product product) {
        product.setName(dto.getName());
        product.setDate(dto.getDate());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgURL(dto.getImgURL());

        product.getCategories().clear();

        for (CategoryDTO categoryDTO : dto.getCategories()) {

            Category category = categoryRepository.getOne(categoryDTO.getId());
            product.getCategories().add(category);
        }

    }

}