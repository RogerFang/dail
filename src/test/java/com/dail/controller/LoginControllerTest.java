package com.dail.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.ServletContext;

import static org.junit.Assert.*;

/**
 * Created by Roger on 2016/12/11.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void login() throws Exception {
        String json = "{\"username\":\"roger\",\"password\":\"1234456\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username","admin")
                .param("password","23456"));
    }

}