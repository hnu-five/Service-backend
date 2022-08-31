package com.citi.training.groupb.servicedemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class SharesHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTransactionSummary() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/history")
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");;
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    void getTransactionSummaryInPeriod() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/history/1W")
        );
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");;
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }
}