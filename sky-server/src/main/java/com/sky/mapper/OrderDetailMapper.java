package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OrderDetailMapper {
    void insertBatch(ArrayList<OrderDetail> orderDetails);


    @Select("select * from order_detail where order_id = #{orderId}")
    List<OrderDetail> getDetailsByOrderId(Long orderId);
}
