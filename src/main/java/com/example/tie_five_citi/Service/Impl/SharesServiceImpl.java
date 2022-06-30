package com.example.tie_five_citi.Service.Impl;

import com.example.tie_five_citi.Entity.Shares;
import com.example.tie_five_citi.Mapper.SharesMapper;
import com.example.tie_five_citi.Service.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("SharesService")
public class SharesServiceImpl implements SharesService {
    @Autowired
    private SharesMapper sharesMapper;

    @Override
    public List<Shares> getShares() {
        List<Shares> shares = new ArrayList<>(Arrays.asList());
        shares.addAll(sharesMapper.getShares());
        return shares;
    }
}
