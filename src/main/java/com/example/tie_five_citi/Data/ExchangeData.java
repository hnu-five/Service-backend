package com.example.tie_five_citi.Data;

import com.example.tie_five_citi.Service.RateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
//1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class ExchangeData {
    @Autowired
    RateDataService rateDataService;

    //3.添加定时任务
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void configureTasks() {
        rateDataService.setRateData();
    }
}
