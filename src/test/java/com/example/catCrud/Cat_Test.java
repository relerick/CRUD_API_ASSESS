package com.example.catCrud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import java.sql.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class Cat_Test {
    @Autowired
    Catrepo repository;

    @Autowired
    MockMvc mvc;

    @Test
    @Transactional
    @Rollback
    public void postTest() throws Exception {
        MockHttpServletRequestBuilder newCat = post("/cat")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Fisker\", \"breed\": \"lion\"}");

        this.mvc.perform(newCat)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Fisker")))
                .andExpect(jsonPath("$.breed", is("lion")));


    }

    @Test
    @Transactional
    @Rollback
    public void getCatTest() throws Exception {
        Cat catOne = new Cat();
        catOne.setName("Fisker");
        catOne.setBreed("lion");
        repository.save(catOne);
        MockHttpServletRequestBuilder oneCatrequest = get("/cat/" + catOne.getId());

        this.mvc.perform(oneCatrequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Fisker")))
                .andExpect(jsonPath("$.breed", is("lion")));


    }

    @Test
    @Transactional
    @Rollback
    public void getAllCats() throws Exception {
        Cat catOne = new Cat();
        catOne.setName("Garfield");
        catOne.setBreed("bengal");
        repository.save(catOne);
        Cat catTwo = new Cat();
        catTwo.setName("Fisker");
        catTwo.setBreed("lion");
        repository.save(catTwo);


        MockHttpServletRequestBuilder getAllCatsRequest = get("/cat");

        this.mvc.perform(getAllCatsRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name", is("Garfield")))
                .andExpect(jsonPath("$.[0].breed", is("bengal")))
                .andExpect(jsonPath("$.[1].name", is("Fisker")))
                .andExpect(jsonPath("$.[1].breed", is("lion")));

    }
    @Test
    @Transactional
    @Rollback
    public void deletedCats() throws Exception {
        Cat catOne = new Cat();
        catOne.setName("Fisker");

        catOne.setBreed("lion");
        repository.save(catOne);
        MockHttpServletRequestBuilder deleteRequest = delete("/cat/" + catOne.getId());


        this.mvc.perform(deleteRequest)
                .andExpect(status().isOk());

    }

    @Test
    @Transactional
    @Rollback
    public void patchCats() throws Exception {
        Cat catOne = new Cat();
        catOne.setName("Garfield");
        catOne.setBreed("bengal");
        repository.save(catOne);
        String JSON = ("{\"name\": \"Garfield\", \"breed\": \"lion\"}");


        MockHttpServletRequestBuilder patchRequest = patch("/cat/" + catOne.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON);


        this.mvc.perform(patchRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Fisker")))
                .andExpect(jsonPath("$.breed", is("bengal")));


    }





}


