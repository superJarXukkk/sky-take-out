package com.sky;

import com.github.pagehelper.Page;
import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    @Scheduled(cron = "0 * * * * ?")
    public void processPayOutOfTime(){
        Page<OrderVO> allPreDetail = orderMapper.getAllPreDetail(Orders.builder().status(Orders.UN_PAID).orderTime(LocalDateTime.now().minusMinutes(15)).build());

        List<OrderVO> result = allPreDetail.getResult();
        result.forEach(res ->{
            res.setCancelReason("订单超时，自动取消");
            res.setStatus(Orders.CANCELLED);
            res.setCancelTime(LocalDateTime.now());
            orderMapper.update(res);
        });

    }


    @Scheduled(cron = "0 0 1 * * ?")
    public void processDelivery(){
        log.info("定时处理处于派送中的订单：{}",LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);

        Page<OrderVO> allPreDetail = orderMapper.getAllPreDetail(Orders.builder().status(Orders.DELIVERY_IN_PROGRESS).orderTime(time).build());
        List<OrderVO> result = allPreDetail.getResult();


        for (OrderVO orderVO : result) {
            orderVO.setStatus(Orders.COMPLETED);
            orderMapper.update(orderVO);
        }


    }
}
