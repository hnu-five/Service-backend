package com.citi.training.groupb.servicedemo.controller;

import com.citi.training.groupb.servicedemo.result.Result;
import com.citi.training.groupb.servicedemo.result.ResultCode;
import com.citi.training.groupb.servicedemo.result.ResultResponse;
import com.citi.training.groupb.servicedemo.service.SalesmanService;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Charlie Du
 * @since 2022-06-30
 */
@RestController
public class SalesmanController {

    private final SalesmanService salesmanService;

    public SalesmanController(SalesmanService salesmanService) {
        this.salesmanService = salesmanService;
    }


//    @RequestMapping(method = RequestMethod.GET, path = "/salesman/{Id}")
//    public Result<Object> getSalesman(@PathVariable Integer Id) {
//        return ResultResponse.getSuccessResult(salesmanService.selectByID(Id));
//    }
}