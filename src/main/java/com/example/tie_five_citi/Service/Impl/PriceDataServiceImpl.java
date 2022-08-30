package com.example.tie_five_citi.Service.Impl;

import com.example.tie_five_citi.Mapper.PriceDataMapper;
import com.example.tie_five_citi.Mapper.TradeDataMapper;
import com.example.tie_five_citi.Service.PriceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service("PriceDataService")
public class PriceDataServiceImpl implements PriceDataService {
    @Autowired
    private PriceDataMapper priceDataMapper;

    @Override
    public void setPriceData() {
        List<String> getRIC = priceDataMapper.getRIC();
        int RICNum = getRIC.size();
        Random rand = new Random();
        for (int i = 0 ; i < RICNum ; i++){
            String RIC = getRIC.get(i);
            int currentPrice = (int) (priceDataMapper.getPrice(RIC)*100);
            int tempPrice = rand.nextInt(currentPrice + 1) + currentPrice/2;
            String tempPrice2 =String.valueOf(tempPrice / 100) + '.' + String.valueOf(tempPrice % 100);
            float newPrice = Float.valueOf(tempPrice2);
            priceDataMapper.changePrice(RIC,newPrice);
        }
    }
}
