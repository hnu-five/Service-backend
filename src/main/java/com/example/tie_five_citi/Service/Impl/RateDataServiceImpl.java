package com.example.tie_five_citi.Service.Impl;

import com.example.tie_five_citi.Mapper.RateDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service("RateDataService")
public class RateDataServiceImpl {
    @Autowired
    private RateDataMapper rateDataMapper;

    public void setRateData() {
        List<String> getCurrency = rateDataMapper.getCurrency();
        int currencyNum = getCurrency.size();
        Random rand = new Random();
        for (int i = 0; i < currencyNum; i++) {
            String currency = getCurrency.get(i);
            int currentRate = (int) (rateDataMapper.getRate(currency) * 10000);
            int tempRate = rand.nextInt(currentRate + 1) + currentRate / 200;
            String tempRate2 = String.valueOf(tempRate / 10000) + '.' + String.valueOf(tempRate % 10000);
            float newRate = Float.valueOf(tempRate2);
            rateDataMapper.changeRate(currency, newRate);
        }
    }
}
