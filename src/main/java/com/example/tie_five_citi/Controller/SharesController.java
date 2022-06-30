package com.example.tie_five_citi.Controller;

import com.example.tie_five_citi.Entity.Shares;
import com.example.tie_five_citi.Service.SharesService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SharesController {

    @Autowired
    private SharesService sharesService;

    @PostMapping("/getShares")
    @CrossOrigin
    public Object getShares(@RequestBody JSONObject getShares) {
        JSONObject jsonObject = new JSONObject();
        List<Shares> shares = sharesService.getShares();
        if (shares == null || shares.size() == 0) {
            jsonObject.put("message", "400");
        } else {
            JSONArray jsonArray = new JSONArray();
            for (Shares warn : shares) {
                JSONObject obj = new JSONObject();
                obj.put("RIC", warn.getRIC());
                obj.put("sharesName",warn.getSharesName());
                obj.put("classId", warn.getClassId());
                obj.put("sharesFlag", warn.getSharesFlag());
                obj.put("sharesNum", warn.getSharesNum());
                obj.put("currencyId", warn.getCurrencyId());
                obj.put("sharesPrice", warn.getSharesPrice());
                obj.put("tradeLimit", warn.getTradeLimit());
                jsonArray.add(obj);
            }
            jsonObject.put("shares",jsonArray);
            jsonObject.put("message","200");
        }
        return jsonObject;
    }
}

