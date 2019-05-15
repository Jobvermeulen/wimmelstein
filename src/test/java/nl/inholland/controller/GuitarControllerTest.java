package nl.inholland.controller;

import nl.inholland.model.Guitar;
import nl.inholland.service.GuitarshopService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GuitarController.class)
public class GuitarControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean private GuitarshopService service;
    private Guitar guitar;

    @Before
    public void setup() {
        guitar = new Guitar("Fender", "Stratocaste", 1750);
    }

    @Test
    public void givenGuitars_whenAllGuitarsShouldReturnJsonArray() throws Exception {
        Iterable<Guitar> allGuitars = Arrays.asList(guitar);
        given(service.getAllGuitars()).willReturn(allGuitars);

        mvc.perform(get("/guitars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].brand").value(guitar.getBrand()));
    }

    @Test
    public void whenCreateGuitarShouldReturnCreated() throws Exception {
        mvc.perform(post("/guitars")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isCreated());

    }
}