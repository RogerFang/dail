package com.dail.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username","admin")
                .param("password","23456"));
    }

}