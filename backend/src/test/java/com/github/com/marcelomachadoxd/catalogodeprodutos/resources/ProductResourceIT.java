package com.github.com.marcelomachadoxd.catalogodeprodutos.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.com.marcelomachadoxd.catalogodeprodutos.DTO.ProductDTO;
import com.github.com.marcelomachadoxd.catalogodeprodutos.Factory;
import com.github.com.marcelomachadoxd.catalogodeprodutos.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenUtil tokenUtil;


    private Long existingId;
    private Long notExistingId;
    private Long countTotalProducts;
    private ProductDTO productDTO;
    private String expectedNameDefault;

    private String username;
    private String password;



    @BeforeEach
    void SetUp() {

        username = "maria@gmail.com";
        password = "123456";

        productDTO = Factory.createProductDTO();

        expectedNameDefault = productDTO.getName();

        existingId = 1L;
        notExistingId = 900000L;
        countTotalProducts = 25L;
    }

    @Test
    public void findAllShouldReturnSortedPageWhenSortById() throws Exception {

        ResultActions result = mockMvc.perform(get("/products/?page=0&size=12&sort=id,asc")
            .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.totalElements").value(countTotalProducts));
        result.andExpect(jsonPath("$.content").exists());
        result.andExpect(jsonPath("$.content[0].id").value("1"));
        result.andExpect(jsonPath("$.content[1].id").value("2"));
        result.andExpect(jsonPath("$.content[2].id").value("3"));
        result.andExpect(jsonPath("$.content[3].id").value("4"));
        result.andExpect(jsonPath("$.content[4].id").value("5"));

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
        result.andExpect(jsonPath("$.name").value(expectedNameDefault));
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



}
