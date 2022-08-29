package com.example.tie_five_citi.Service.Impl;

import com.example.tie_five_citi.Entity.SharesHold;
import com.example.tie_five_citi.Mapper.TradeDataMapper;
import com.example.tie_five_citi.Service.TradeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service("TradeDataService")
public class TradeDataServiceImpl implements TradeDataService {
    @Autowired
    private TradeDataMapper tradeDataMapper;

    @Override
    public void setTradeData() {
        List<String> getUserID = tradeDataMapper.getUser();
        List<String> getRIC = tradeDataMapper.getRIC();
        List<String> getSalesman = tradeDataMapper.getSales();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now(); // get the current time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
        String set_time = date.format(formatter)+" "+time.format(formatter1);
        int u_num = getUserID.size();
        int R_num = getRIC.size();
        for (int i = 0 ; i < u_num ; i++){
            for (int j = 0 ; j < R_num ; j++) {
                Random rand = new Random();
                String user_id = getUserID.get(i);
                String RIC = getRIC.get(j);
                SharesHold result = tradeDataMapper.getHold(RIC, user_id);
                if (result == null) {
                    tradeDataMapper.setHold(RIC,user_id,0);
                }
                float price = tradeDataMapper.getShares(RIC).getSharesPrice();
                int shares_num = tradeDataMapper.getShares(RIC).getSharesNum();
                int limit = tradeDataMapper.getShares(RIC).getTradeLimit();
                String current_hold = String.valueOf(tradeDataMapper.getHold(RIC,user_id).getCurrent_hold());
                int hold = tradeDataMapper.getHold(RIC,user_id).getCurrent_hold();
                if (hold == 0){
                    if (shares_num == 0)
                    {
                        continue;
                    }
                    int size = 0;
                    if (shares_num > limit) {
                        size = rand.nextInt(limit) + 1;
                    }
                    else
                    {
                        size = rand.nextInt(shares_num) + 1;
                    }
                    String salesman_id = getSalesman.get(rand.nextInt(10));
                    tradeDataMapper.setData(RIC,user_id,size,set_time,salesman_id,price,1);
                    tradeDataMapper.changeHold(RIC,user_id,hold + size);
                    tradeDataMapper.changeSharesNum(RIC,shares_num - size);
                }
                else {
                    if (shares_num == 0)
                    {
                        int size = rand.nextInt(hold)+1;
                        size = -size;
                        String salesman_id = getSalesman.get(rand.nextInt(10));
                        tradeDataMapper.setData(RIC,user_id,size,set_time,salesman_id,price,0);
                        tradeDataMapper.changeHold(RIC,user_id,hold + size);
                        tradeDataMapper.changeSharesNum(RIC,shares_num - size);
                    }
                    else{
                        int trade_flag = rand.nextInt(2);
                        if(trade_flag == 0){
                            int size = rand.nextInt(hold)+1;
                            size = -size;
                            String salesman_id = getSalesman.get(rand.nextInt(10));
                            tradeDataMapper.setData(RIC,user_id,size,set_time,salesman_id,price,0);
                            tradeDataMapper.changeHold(RIC,user_id,hold + size);
                            tradeDataMapper.changeSharesNum(RIC,shares_num - size);
                        }
                        else if(trade_flag == 1){
                            int size =0;
                            int max_size = limit - Integer.parseInt(current_hold);
                            if(shares_num >= max_size){
                                size = rand.nextInt(max_size) + 1;
                            }
                            else if(shares_num < max_size){
                                size = rand.nextInt(shares_num) + 1;
                            }
                            String salesman_id = getSalesman.get(rand.nextInt(10));
                            tradeDataMapper.setData(RIC,user_id,size,set_time,salesman_id,price,1);
                            tradeDataMapper.changeHold(RIC,user_id,hold + size);
                            tradeDataMapper.changeSharesNum(RIC,shares_num - size);
                        }
                    }
                }
            }
        }
    }
}
