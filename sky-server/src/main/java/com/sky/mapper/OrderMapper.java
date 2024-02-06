package com.sky.mapper;


import com.github.pagehelper.Page;
import com.sky.entity.Orders;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {


    void insert(Orders orders);

    @Select("select * from orders where number = #{orderNumber} and user_id= #{userId}")
    Orders getByNumberAndUserId(String outTradeNo, Long userId);

    void update(Orders orders);
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    @Update("update orders set status = #{orderStatus},pay_status = #{orderPaidStatus} ,checkout_time = #{check_out_time} where number = #{orderNumber}")
    void updateStatus(Integer orderStatus, Integer orderPaidStatus, LocalDateTime check_out_time, String orderNumber);


    @Select("select * from orders where id = #{id}")
    Orders getById(Long id);

    Page<OrderVO> getAllPreDetail(Orders orders);

    Integer countStatus(Integer toBeConfirmed);

    @Select("select * from orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);
}
