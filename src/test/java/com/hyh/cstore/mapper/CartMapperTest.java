package com.hyh.cstore.mapper;

import com.hyh.cstore.entity.Cart;
import com.hyh.cstore.vo.CartVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class CartMapperTest {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(4);
        cart.setPid(2);
        cart.setNum(3);
        cart.setPrice(4L);
        Integer rows = cartMapper.insertCart(cart);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateNumByCid() {
        Integer cid = 1;
        Integer num = 10;
        String modifiedUser = "购物车管理员";
        Date modifiedTime = new Date();
        Integer rows = cartMapper.updateNumByCid(num, cid, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByUidAndPid() {
        Integer uid = 4;
        Integer pid = 2;
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        System.out.println(result);
    }
    @Test
    public void findCartList() {
        Integer uid = 4;
        List<CartVO> cartListByUid = cartMapper.findCartListByUid(uid);
        for(CartVO c:cartListByUid){
            System.out.println(c);
        }
    }
    @Test
    public void findByCid(){
        System.out.println(cartMapper.findByCid(2));
    }
    @Test
    public void findByCids() {
        Integer[] cids = {3, 4};
        List<CartVO> list = cartMapper.findCartListByCid(cids);
        System.out.println("count=" + list.size());
        for (CartVO item : list) {
            System.out.println(item);
        }
    }
}
