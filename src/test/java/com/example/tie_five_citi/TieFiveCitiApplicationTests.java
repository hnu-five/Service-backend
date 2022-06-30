package com.example.tie_five_citi;

import com.alibaba.fastjson.JSONObject;
import com.example.tie_five_citi.Controller.SharesController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TieFiveCitiApplicationTests {
    @Autowired
    SharesController sharesController;

    @Test
    void contextLoads() {
        JSONObject jsonObject = new JSONObject();
        System.out.println(sharesController.getShares(jsonObject));
    }
}
