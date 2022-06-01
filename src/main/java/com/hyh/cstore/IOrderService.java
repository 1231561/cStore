package com.hyh.cstore;

import com.hyh.cstore.entity.Order;
import com.hyh.cstore.entity.OrderItem;
import com.hyh.cstore.vo.OrderVO;

import java.util.List;

public interface IOrderService {
    Order CreateOrder(Integer uid, Integer[] cids, Integer aid, String username);

    List<OrderVO> showOrderItem(Integer uid);

    void deleteOrderItemById(Integer id);
}
