package com.hyh.cstore.controller;

import com.hyh.cstore.IOrderService;
import com.hyh.cstore.entity.Order;
import com.hyh.cstore.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController{
    @Autowired
    IOrderService orderService;
    @RequestMapping("create_order")
    public JsonResult<Order> createOrder(HttpSession session, Integer[] cids, Integer aid){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Order order = orderService.CreateOrder(uid, cids, aid, username);
        return new JsonResult<>(OK, order);
    }
}
