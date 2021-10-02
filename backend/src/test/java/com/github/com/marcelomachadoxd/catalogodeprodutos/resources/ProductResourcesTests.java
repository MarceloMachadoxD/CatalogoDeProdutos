package com.github.com.marcelomachadoxd.catalogodeprodutos.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.ProductDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.Factory;
import com.github.com.marcelomachadoxd.catalogodeprodutos.TokenUtil;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.ProductService;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions.DatabaseException;
import com.github.com.marcelomachadoxd.catalogodeprodutos.services.exeptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductResourcesTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenUtil tokenUtil;



    private Long existingId;
    private Long notExistingId;
    private Long dependentID;
    private ProductDTO productDTO;
    private PageImpl<ProductDTO> page;

    private String username;
    private String password;

    @BeforeEach
    void SetUp() throws Exception{

        username = "maria@gmail.com";
        password = "123456";

        existingId = 1L;
        notExistingId = 9000L;
        dependentID = 3L;

        productDTO = Factory.createProductDTO();
        page = new PageImpl<>(List.of(productDTO));
        when(productService.findAllPaged(any(),any(),any())).thenReturn(page);

        when(productService.findById(existingId)).thenReturn(productDTO);

        when(productService.findById(notExistingId)).thenThrow(ResourceNotFoundException.class);

        when(productService.update(eq(existingId), any())).thenReturn(productDTO);
        when(productService.update(eq(notExistingId), any())).thenThrow(ResourceNotFoundException.class);

        doNothing().when(productService).deleteProductById(existingId);
        doThrow(ResourceNotFoundException.class).when(productService).deleteProductById(notExistingId);
        doThrow(DatabaseException.class).when(productService).deleteProductById(dependentID);


        when(productService.insert(any())).thenReturn(productDTO);

    }


    @Test
    public void findAllShouldReturnPage() throws Exception{
        ResultActions result = mockMvc.perform(get("/products")
            .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
    }


    @Test
    public void findByIDShouldReturnProductWhenIdExists() throws Exception{

        ResultActions result = mockMvc.perform(get("/products/{id}", existingId)
            .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());

    }

    @Test
    public void findByIDShouldReturnNotFoundWhenIdNotExists() throws Exception{

        ResultActions result = mockMvc.perform(get("/products/{id}", notExistingId)
            .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isNotFound());

    }

    @Test
    public void updateShouldReturnProductDTOWhenIdExists() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(put("/products/{id}", existingId)
            .content(jsonBody)
            .header("Authorization", "Bearer " + accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());

    }

    @Test
    public void updateShouldReturnNotFoundWhenIdNotExists() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(put("/products/{id}", notExistingId)
            .content(jsonBody)
            .header("Authorization", "Bearer " + accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isNotFound());

    }

    @Test
    public void DeleteShouldReturnNoContentWhenIdExists() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        ResultActions result = mockMvc.perform(delete("/products/{id}", existingId)
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isNoContent());

    }

    @Test
    public void DeleteShouldReturnNotFoundWhenIdNotExists() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        ResultActions result = mockMvc.perform(delete("/products/{id}", notExistingId)
            .header("Authorization", "Bearer " + accessToken)
            .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isNotFound());

    }


    @Test
    public void InsertShouldReturnCreatedAndProductDTO() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(post("/products")
            .content(jsonBody)
            .header("Authorization", "Bearer " + accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());

    }


}
