package com.citi.training.groupb.eurekaserver;

import com.alibaba.fastjson.JSON;
import com.citi.training.groupb.servicedemo.vo.TransactionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
<<<<<<< HEAD:EurekaServer/src/test/java/com/citi/training/groupb/eurekaserver/EurekaServerApplicationTests.java
class EurekaServerApplicationTests {
=======
@AutoConfigureMockMvc
class ServiceDemoApplicationTests {
>>>>>>> 52b0f5011203f69c26b63d0db4a661d254769758:src/test/java/com/citi/training/groupb/servicedemo/ServiceDemoApplicationTests.java

}
