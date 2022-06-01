package com.hyh.cstore.mapper;

import com.hyh.cstore.entity.Order;
import com.hyh.cstore.entity.OrderItem;
import com.hyh.cstore.entity.Product;
import com.hyh.cstore.vo.CartVO;
import com.hyh.cstore.vo.OrderVO;

import java.util.List;

public interface OrderMapper {
    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);

    List<OrderVO> findOrderItemByUid(Integer uid);

    Integer deleteOrderById(Integer id);

    OrderItem findOrderItemById(Integer id);


}
