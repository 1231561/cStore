package com.hyh.cstore.mapper;

import com.hyh.cstore.entity.Order;
import com.hyh.cstore.entity.OrderItem;

public interface OrderMapper {
    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);
}
