package com.sharding.demo.dao;

import com.sharding.demo.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {


    @Insert(" insert into t_order (status, user_id) values(#{status},#{userId})")
    int insertTest(Order order);
}