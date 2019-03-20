package ru.calculate.controller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.calculate.CalculateApplication;
import ru.calculate.model.Expression;
import ru.calculate.service.Calculate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalculateApplication.class)
public class RootControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void calculateDiv() throws Exception {
        mockMvc.perform(post("/div").contentType(MediaType.APPLICATION_JSON)
                .content("{\"a\":\"100\",\"b\":\"2\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void calculateAdd() throws Exception {
        mockMvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON)
                .content("{\"a\":\"100\",\"b\":\"2\"}"))
                .andExpect(status().isOk());
    }
    @Test
    public void calculateSub() throws Exception {
        mockMvc.perform(post("/sub").contentType(MediaType.APPLICATION_JSON)
                .content("{\"a\":\"100\",\"b\":\"2\"}"))
                .andExpect(status().isOk());
    }
    @Test
    public void calculateMul() throws Exception {
        mockMvc.perform(post("/mul").contentType(MediaType.APPLICATION_JSON)
                .content("{\"a\":\"100\",\"b\":\"2\"}"))
                .andExpect(status().isOk());
    }
}