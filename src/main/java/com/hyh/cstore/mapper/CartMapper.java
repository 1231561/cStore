package com.hyh.cstore.mapper;

import com.hyh.cstore.entity.Cart;
import com.hyh.cstore.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    Integer insertCart(Cart cart);

    Integer updateNumByCid(Integer num, Integer cid, String modifiedUser, Date modifiedTime);

    Cart findByUidAndPid(Integer uid, Integer pid);

    List<CartVO> findCartListByUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVO> findCartListByCid(Integer[] cids);

}
