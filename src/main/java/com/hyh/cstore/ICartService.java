package com.hyh.cstore;

import com.hyh.cstore.vo.CartVO;

import java.util.List;

/**将添加商品数量的业务层接口*/
public interface ICartService {
    /**
     * 添加商品到购物车
     * @param uid 用户名
     * @param pid 商品名
     * @param num 添加商品的数量
     * @param username 修改者名
     */
    void addToCart(Integer uid, Integer pid, Integer num, String username);

    List<CartVO> showCartListByUid(Integer uid);

    Integer addNumber(Integer cid, Integer uid, String username);

    List<CartVO> showCartListByCid(Integer[] cids, Integer uid);
}
