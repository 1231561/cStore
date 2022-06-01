package com.hyh.cstore.service;

import com.hyh.cstore.IOrderService;
import com.hyh.cstore.entity.Order;
import com.hyh.cstore.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private IOrderService orderService;

    @Test
    public void create() {
        try {
            Integer aid = 13;
            Integer[] cids = {3, 4};
            Integer uid = 4;
            String username = "订单管理员";
            Order order = orderService.CreateOrder(uid, cids, aid, username,1);
            System.out.println(order);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}