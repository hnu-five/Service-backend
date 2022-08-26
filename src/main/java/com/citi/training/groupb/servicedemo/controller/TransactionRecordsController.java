package com.citi.training.groupb.servicedemo.controller;

import com.citi.training.groupb.servicedemo.entity.TransactionView;
import com.citi.training.groupb.servicedemo.result.Result;
import com.citi.training.groupb.servicedemo.result.ResultCode;
import com.citi.training.groupb.servicedemo.result.ResultResponse;
import com.citi.training.groupb.servicedemo.service.TransactionRecordsService;
import com.citi.training.groupb.servicedemo.vo.TransactionRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Charlie Du
 * @since 2022-06-25
 */
@RestController
@RequestMapping("/servicedemo/transactionRecords")
public class TransactionRecordsController {
    private final TransactionRecordsService transactionRecordsService;

    public TransactionRecordsController(TransactionRecordsService transactionRecordsService) {
        this.transactionRecordsService = transactionRecordsService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/insert")
    public Result<Object> insertOneTransaction(@RequestBody TransactionRequest transactionRequest) {
        int res = transactionRecordsService.insertOneTransaction(transactionRequest);
        if (res > 0) {
            Result<Object> failedRes = new Result<>();
            failedRes.setStatus(ResultCode.BODY_NOT_MATCH.getResultCode());
            String failedMsg = ResultCode.BODY_NOT_MATCH.getResultMsg();
            switch (res) {
                case 1 -> failedMsg += " 请检查输入的 Client Name";
                case 2 -> failedMsg += " 请检查输入的 RIC";
                case 3 -> failedMsg += " 请检查输入的 Salesman";
                case 4 -> failedMsg += " 请检查输入的 Currency";
                default -> {}
            }
            failedRes.setMessage(failedMsg);
            return failedRes;
        }
        return ResultResponse.getSuccessResult();
    }
}