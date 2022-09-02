package com.example.tie_five_citi.Mapper;

import com.example.tie_five_citi.Entity.Shares;
import com.example.tie_five_citi.Entity.SharesHold;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeDataMapper {
    void setData(String RIC, String user_id, int size,String time,String salesman,String currency,float price,String flag,int hold,String sector,int mode);
    List<SharesHold> getHold(String RIC, String user_id);
    Shares getShares(String RIC);
    void changeSharesNum(String RIC,int num);
    List<String> getRIC();
    List<String> getUser();
    List<String> getSales();
    List<String> getCurrency();
    String getSharesCurrency(String RIC);
    float getRate(String currencyID);
    void setHistory(String RIC,float closingPrice,int transactionIn,int transactionOut,String date);
}
