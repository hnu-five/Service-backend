package com.example.tie_five_citi.Mapper;

import com.example.tie_five_citi.Entity.Shares;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SharesMapper {
    List<Shares> getShares();
}
