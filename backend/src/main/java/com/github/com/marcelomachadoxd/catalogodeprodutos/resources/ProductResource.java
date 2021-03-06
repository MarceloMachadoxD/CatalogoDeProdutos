package com.github.com.marcelomachadoxd.catalogodeprodutos.resources;

import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.CategoryDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.ProductDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
            @RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
            @RequestParam(value = "name", defaultValue = "") String name,
            Pageable pageable

/*        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "name") String orderBy*/

    ) {

/*
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
*/

        Page<ProductDTO> list = productService.findAllPaged(categoryId, name.trim(), pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findByID(@PathVariable Long id){
        ProductDTO productDTO = productService.findById(id);

        return ResponseEntity.ok().body(productDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insertProduct(@Valid @RequestBody ProductDTO dto){
        dto = productService.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody ProductDTO dto){
        dto = productService.update(id, dto);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);

        return ResponseEntity.noContent().build();
    }







}
