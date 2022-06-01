package com.hyh.cstore.controller;

import com.hyh.cstore.IOrderService;
import com.hyh.cstore.entity.Order;
import com.hyh.cstore.entity.OrderItem;
import com.hyh.cstore.util.JsonResult;
import com.hyh.cstore.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController{
    @Autowired
    IOrderService orderService;
    @RequestMapping("create_order")
    public JsonResult<Order> createOrder(HttpSession session, Integer[] cids, Integer aid, Integer pid){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Order order = orderService.CreateOrder(uid, cids, aid, username,pid);
        return new JsonResult<>(OK, order);
    }
    @RequestMapping("show")
    public JsonResult<List<OrderVO>> showOrderItems(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<OrderVO> list = orderService.showOrderItem(uid);
        return new JsonResult<>(OK,list);
    }
    @RequestMapping("delete")
    public JsonResult<Void> deleteOrderItems(Integer id){
        orderService.deleteOrderItemById(id);
        return new JsonResult<>(OK);
    }
}
