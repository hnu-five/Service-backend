package com.example.tie_five_citi.Mapper;

import com.example.tie_five_citi.Entity.Shares;
import com.example.tie_five_citi.Entity.SharesHold;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeDataMapper {
    void setData(String RIC, String user_id, int size,String time,String salesman,float price,int flag,int hold);
    List<SharesHold> getHold(String RIC, String user_id);
    void changeHold(String RIC,String user_id,int temp);
    void setHold(String RIC,String user_id,int temp);
    Shares getShares(String RIC);
    void changeSharesNum(String RIC,int num);
    List<String> getRIC();
    List<String> getUser();
    List<String> getSales();
}
