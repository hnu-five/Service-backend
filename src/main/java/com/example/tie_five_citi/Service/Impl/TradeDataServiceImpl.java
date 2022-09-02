package com.example.tie_five_citi.Service.Impl;

import com.example.tie_five_citi.Entity.SharesHold;
import com.example.tie_five_citi.Mapper.TradeDataMapper;
import com.example.tie_five_citi.Service.TradeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service("TradeDataService")
public class TradeDataServiceImpl implements TradeDataService {
    @Autowired
    private TradeDataMapper tradeDataMapper;

    @Override
    public void setTradeData() {
        List<String> getUserID = tradeDataMapper.getUser();
        List<String> getRIC = tradeDataMapper.getRIC();
        List<String> getSalesman = tradeDataMapper.getSales();
        List<String> getCurrency = tradeDataMapper.getCurrency();
        String[] sectors = {"NYSE","NASDAQ","LSE","FESE","HKEx","SHSE","ASX","FWB"};
        Random rand = new Random();
//        LocalDate date = LocalDate.now();
//        LocalTime time = LocalTime.now(); // get the current time
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String set_time = date.format(formatter)+" "+time.format(formatter1);
        int u_num = getUserID.size();
        int R_num = getRIC.size();
        int hold =0;
        int numLimit = 6;
        for(int k=8; k<9; k++)
        {
            String month = String.valueOf(k+1);
            if(k+1 < 10){
                month = "0"+month;
            }
            float closingPrice = 0;
            int totalBuy = 0;
            int totalSell = 0;
            String yeah = "2022";
            for(int l=0; l<1; l++)
            {
                String day = "";
//                int dayNum1 = rand.nextInt(5)+1;
//                int dayNum2 = rand.nextInt(5)+5;
//                int dayNum3 = rand.nextInt(5)+10;
//                int dayNum4 = rand.nextInt(5)+15;
//                int dayNum5 = rand.nextInt(5)+20;
//                int dayNum6 = rand.nextInt(5)+24;
                int dayNum1 = 2;
//                int dayNum2 = 29;
//                int dayNum3 = 30;
//                int dayNum4 = 31;
//                if (l == 0){
//                    day = "0"+String.valueOf(dayNum1);
//                }
//                else if(l == 1){
//                    day = "0"+String.valueOf(dayNum2);
//                }
//                else if(l == 2){
//                    day = String.valueOf(dayNum3);
//                }
//                else if (l == 3){
//                    day = String.valueOf(dayNum4);
//                }
//                else if(l == 4){
//                    day = String.valueOf(dayNum5);
//                }
//                else if(l == 5){
//                    day = String.valueOf(dayNum6);
//                }
                if (l == 0){
                    day = "0"+String.valueOf(dayNum1);
                }
//                else if(l == 1){
//                    day = String.valueOf(dayNum2);
//                }
//                else if(l == 2){
//                    day = String.valueOf(dayNum3);
//                }
//                else if (l == 3){
//                    day = String.valueOf(dayNum4);
//                }
                int endFlag = 0;
                String endRIC = "";
                for (int i = 0 ; i < u_num ; i++){
                    for (int j = 0 ; j < R_num ; j++) {
                        if (i == u_num-1 && j == R_num-1){
                            endFlag = 1;
                        }
                        int whetherTrade = rand.nextInt(numLimit);
                        if(whetherTrade != 0)
                        {
                            continue;
                        }
                        String hour = String.valueOf(rand.nextInt(24));
                        String minute = String.valueOf(rand.nextInt(60));
                        String second = String.valueOf(rand.nextInt(60));
                        if(Integer.valueOf(hour) < 10){
                            hour = "0" + hour;
                        }
                        if(Integer.valueOf(minute) < 10){
                            minute = "0" + minute;
                        }
                        if(Integer.valueOf(second) < 10){
                            second = "0" + second;
                        }
                        String set_time = yeah+"-"+month+"-"+day+" "+hour+"-"+minute+"-"+second;
                        String user_id = getUserID.get(i);
                        String RIC = getRIC.get(j);
                        endRIC = RIC;
                        String sector = sectors[rand.nextInt(sectors.length)];
                        String currency = getCurrency.get(rand.nextInt(getCurrency.size()));
                        float rate = tradeDataMapper.getRate(currency);
                        int mode = rand.nextInt(2);
                        List<SharesHold> result = tradeDataMapper.getHold(RIC, user_id);
                        result = result.stream().sorted(Comparator.comparing(SharesHold::getUser_id).thenComparing(SharesHold::getRIC).thenComparing(SharesHold::getLastTime).reversed()).collect(Collectors.toList());
                        if (result.isEmpty()) {
                            hold =0 ;
                        }
                        else{
                            hold = result.get(0).getCurrent_hold();
                        }
                        float sharesRate = tradeDataMapper.getRate(tradeDataMapper.getShares(RIC).getCurrencyId());
                        closingPrice = tradeDataMapper.getShares(RIC).getSharesPrice() * sharesRate;
                        float price = closingPrice / rate;
                        int shares_num = tradeDataMapper.getShares(RIC).getSharesNum();
                        int limit = tradeDataMapper.getShares(RIC).getTradeLimit();
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
                            String salesman_id = getSalesman.get(rand.nextInt(getSalesman.size()));
                            tradeDataMapper.setData(RIC,user_id,size,set_time,salesman_id,currency,price,"buy",hold + size,sector,mode);
                            tradeDataMapper.changeSharesNum(RIC,shares_num - size);
                            totalBuy = totalBuy + size;
                        }
                        else {
                            if (shares_num == 0)
                            {
                                int size = rand.nextInt(hold)+1;
                                String salesman_id = getSalesman.get(rand.nextInt(getSalesman.size()));
                                tradeDataMapper.setData(RIC,user_id,size,set_time,salesman_id,currency,price,"sell",hold - size,sector,mode);
                                tradeDataMapper.changeSharesNum(RIC,shares_num + size);
                                totalSell = totalSell + size;
                            }
                            else{
                                int trade_flag = rand.nextInt(2);
                                if(trade_flag == 0){
                                    int size = rand.nextInt(hold)+1;
                                    String salesman_id = getSalesman.get(rand.nextInt(getSalesman.size()));
                                    tradeDataMapper.setData(RIC,user_id,size,set_time,salesman_id,currency,price,"sell",hold - size,sector,mode);
                                    tradeDataMapper.changeSharesNum(RIC,shares_num + size);
                                    totalSell = totalSell + size;
                                }
                                else if(trade_flag == 1){
                                    int size =0;
                                    int max_size = limit - hold;
                                    if(shares_num >= max_size){
                                        size = rand.nextInt(max_size) + 1;
                                    }
                                    else if(shares_num < max_size){
                                        size = rand.nextInt(shares_num) + 1;
                                    }
                                    String salesman_id = getSalesman.get(rand.nextInt(getSalesman.size()));
                                    tradeDataMapper.setData(RIC,user_id,size,set_time,salesman_id,currency,price,"buy",hold + size,sector,mode);
                                    tradeDataMapper.changeSharesNum(RIC,shares_num - size);
                                    totalBuy = totalBuy + size;
                                }
                            }
                        }
                    }
                }
                if(endFlag == 1){
                    String recordDate = yeah+"-"+month+"-"+day;
                    tradeDataMapper.setHistory(endRIC,closingPrice,totalBuy,totalSell,recordDate);
                }
            }
        }
    }
}
