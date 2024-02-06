package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);

    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    void paySuccess(String outTradeNo);

    OrderVO getDetailByid(Long id);

    PageResult getHistoryOrders(int page, int pageSize, int status);

    void cancelOrder(Long id);

    void repetition(Long id);

    void complete(Long id);

    void delivery(Long id);

    void cancel(OrdersCancelDTO ordersCancelDTO) throws Exception;

    void rejection(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    OrderVO details(Long id);

    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    OrderStatisticsVO statistics();

    void reminder(Long id);
}
