package com.citi.training.groupb.servicedemo.service;

import com.citi.training.groupb.servicedemo.entity.TransactionRecords;
import com.baomidou.mybatisplus.extension.service.IService;
import com.citi.training.groupb.servicedemo.entity.TransactionView;
import com.citi.training.groupb.servicedemo.vo.TransactionRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Charlie Du
 * @since 2022-06-25
 */
public interface TransactionRecordsService extends IService<TransactionRecords> {
    int insertOneTransaction(TransactionRequest transactionRequest);
}