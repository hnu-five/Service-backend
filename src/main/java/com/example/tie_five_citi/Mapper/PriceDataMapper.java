package com.example.tie_five_citi.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PriceDataMapper {
    void changePrice(String RIC,float newPrice);
    float getPrice(String RIC);
    List<String> getRIC();
}
