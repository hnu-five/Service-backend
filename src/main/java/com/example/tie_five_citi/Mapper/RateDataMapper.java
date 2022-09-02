package com.example.tie_five_citi.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RateDataMapper {
    void changeRate(String currencyId,float newRate);
    float getRate(String currencyId);
    List<String> getCurrency();
}
