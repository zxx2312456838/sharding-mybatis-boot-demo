package com.sharding.demo.controller;

import com.sharding.demo.entity.Order;
import com.sharding.demo.service.TestService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("test")
public class TestController {


    @Autowired
    private TestService testService;

    /**
     * 两阶段事务--同库
     */
    @ApiOperation(value = "两阶段事务--同库", notes = "两阶段事务--同库")
    @GetMapping("transXaSameDb")
    public String transXaSameDb() {

        Order order = new Order();
        order.setUserId(0);
        order.setStatus("INSERT_TEST_TRANS");
        Order order2 = new Order();
        order2.setUserId(0);
        order2.setStatus("INSERT_TEST_TRANS");

        String tt = testService.transXa(order, order2);
        return tt;
    }

    /**
     * 两阶段事务--异库
     */
    @ApiOperation(value = "两阶段事务--异库", notes = "两阶段事务--异库")
    @GetMapping("transXaDiffDb")
    public String transXaDiffDb() {

        Order order = new Order();
        order.setUserId(0);
        order.setStatus("INSERT_TEST_TRANS");
        Order order2 = new Order();
        order2.setUserId(1);
        order2.setStatus("INSERT_TEST_TRANS");

        String tt = testService.transXa(order, order2);
        return tt;
    }


    /**
     * 柔性事务--同库
     */
    @ApiOperation(value = "柔性事务--同库", notes = "柔性事务--同库")
    @GetMapping("transSagaSameDb")
    public String transSagaSameDb() {

        Order order = new Order();
        order.setUserId(0);
        order.setStatus("INSERT_TEST_TRANS");
        Order order2 = new Order();
        order2.setUserId(0);
        order2.setStatus("INSERT_TEST_TRANS");

        String tt = testService.transBase(order, order2);
        return tt;
    }

    /**
     * 柔性事务--异库
     */
    @ApiOperation(value = "柔性事务--异库", notes = "柔性事务--异库")
    @GetMapping("transSagaDiffDb")
    public String transSagaDiffDb() {

        Order order = new Order();
        order.setUserId(1);
        order.setStatus("INSERT_TEST_TRANS");
        Order order2 = new Order();
        order2.setUserId(0);
        order2.setStatus("INSERT_TEST_TRANS");

        String tt = testService.transBase(order, order2);
        return tt;
    }
}