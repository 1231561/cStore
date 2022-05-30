package com.hyh.cstore;

import com.hyh.cstore.entity.Order;

public interface IOrderService {
    Order CreateOrder(Integer uid, Integer[] cids, Integer aid, String username);
}
