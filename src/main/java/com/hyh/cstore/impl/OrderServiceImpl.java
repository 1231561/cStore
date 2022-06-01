package com.hyh.cstore.impl;

import com.hyh.cstore.IAddressService;
import com.hyh.cstore.ICartService;
import com.hyh.cstore.IOrderService;
import com.hyh.cstore.entity.Address;
import com.hyh.cstore.entity.Order;
import com.hyh.cstore.entity.OrderItem;
import com.hyh.cstore.entity.Product;
import com.hyh.cstore.ex.DeleteException;
import com.hyh.cstore.ex.InsertException;
import com.hyh.cstore.ex.OrderItemNotFoundException;
import com.hyh.cstore.mapper.OrderMapper;
import com.hyh.cstore.mapper.ProductMapper;
import com.hyh.cstore.vo.CartVO;
import com.hyh.cstore.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    ICartService cartService;
    @Autowired
    IAddressService addressService;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ProductMapper productMapper;
    @Override
    public Order CreateOrder(Integer uid, Integer[] cids, Integer aid, String username, Integer pid) {
        if(pid != null){
            Product p = productMapper.findById(pid);
            Address address = addressService.getAddress(aid, uid);
            Order order = new Order();
            order.setUid(uid);
            order.setModifiedTime(new Date());
            order.setRecvName(address.getName());
            order.setOrderTime(new Date());
            order.setRecvAddress(address.getAddress());
            order.setRecvProvince(address.getProvinceName());
            order.setRecvCity(address.getCityName());
            order.setRecvArea(address.getAreaName());
            order.setRecvPhone(address.getPhone());
            order.setTotalPrice(p.getPrice());
            order.setStatus(0);
            order.setCreatedUser(username);
            order.setCreatedTime(new Date());
            order.setModifiedUser(username);
            Integer integer = orderMapper.insertOrder(order);
            if(integer != 1){
                throw new InsertException("创建订单失败");
            }
                OrderItem orderItem = new OrderItem();
                orderItem.setPid(pid);
                orderItem.setNum(1);
                orderItem.setPrice(p.getPrice());
                orderItem.setTitle(p.getTitle());
                orderItem.setImage(p.getImage());
                orderItem.setOid(order.getOid());
                orderItem.setCreatedTime(new Date());
                orderItem.setCreatedUser(username);
                orderItem.setModifiedTime(new Date());
                orderItem.setModifiedUser(username);
                Integer integer1 = orderMapper.insertOrderItem(orderItem);
                if(integer1 != 1){
                    throw new InsertException("创建订单项失败");
            }
                return order;
        }
        List<CartVO> list = cartService.showCartListByCid(cids,uid);
        Long totalPrice = 0L;
        for (CartVO l : list){
            totalPrice += l.getRealPrice() * l.getNum();
        }
        Address address = addressService.getAddress(aid, uid);
        Order order = new Order();
        order.setUid(uid);
        order.setModifiedTime(new Date());
        order.setRecvName(address.getName());
        order.setOrderTime(new Date());
        order.setRecvAddress(address.getAddress());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvPhone(address.getPhone());
        order.setTotalPrice(totalPrice);
        order.setStatus(0);
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        Integer integer = orderMapper.insertOrder(order);
        if(integer != 1){
            throw new InsertException("创建订单失败");
        }
        for (CartVO l : list){
            OrderItem orderItem = new OrderItem();
            orderItem.setPid(l.getPid());
            orderItem.setNum(l.getNum());
            orderItem.setPrice(l.getRealPrice());
            orderItem.setTitle(l.getTitle());
            orderItem.setImage(l.getImage());
            orderItem.setOid(order.getOid());
            orderItem.setCreatedTime(new Date());
            orderItem.setCreatedUser(username);
            orderItem.setModifiedTime(new Date());
            orderItem.setModifiedUser(username);
            Integer integer1 = orderMapper.insertOrderItem(orderItem);
            if(integer1 != 1){
                throw new InsertException("创建订单项失败");
            }
        }
        return order;
    }

    @Override
    public List<OrderVO> showOrderItem(Integer uid) {
        List<OrderVO> orders = orderMapper.findOrderItemByUid(uid);
        return orders;
    }

    @Override
    public void deleteOrderItemById(Integer id) {
        OrderItem orderItem = orderMapper.findOrderItemById(id);
        if(orderItem == null){
            throw new OrderItemNotFoundException("该订单项不存在");
        }
        Integer integer = orderMapper.deleteOrderById(id);
        if(integer != 1){
            throw new DeleteException("删除失败");
        }
    }

}
