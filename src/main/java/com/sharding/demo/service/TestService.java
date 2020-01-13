package com.sharding.demo.service;

import com.sharding.demo.dao.TestMapper;
import com.sharding.demo.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Random;

@Service
@Slf4j
public class TestService {


    @Autowired
    private TestMapper testMapper;


    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public String transXa(Order order, Order order2) {
        try {

            int i = testMapper.insertTest(order);
            int e = 5 / new Random().nextInt(2);
            int i1 = testMapper.insertTest(order2);

            return i + "--" + i1;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("测试事务XA-异常 ：[{}]", e.getMessage());
        }
        return "0";
    }

    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public String transBase(Order order, Order order2) {
        try {

            int i = testMapper.insertTest(order);
            int e = 5 / new Random().nextInt(2);
            int i1 = testMapper.insertTest(order2);

            return i + "--" + i1;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("测试事务BASE-异常 ：[{}]", e.getMessage());
        }
        return "0";
    }
}