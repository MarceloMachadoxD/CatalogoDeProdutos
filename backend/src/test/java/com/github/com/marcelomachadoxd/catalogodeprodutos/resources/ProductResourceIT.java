package com.github.com.marcelomachadoxd.catalogodeprodutos.resources;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductResourceIT {

    @Autowired
    private MockMvc mockMvc;


    private Long existingId;
    private Long notExistingId;
    private Long countTotalProducts;


    @BeforeEach
    void SetUp() {

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


}
