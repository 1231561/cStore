package com.hyh.cstore.impl;

import com.hyh.cstore.ICartService;
import com.hyh.cstore.entity.Cart;
import com.hyh.cstore.entity.Product;
import com.hyh.cstore.ex.*;
import com.hyh.cstore.mapper.CartMapper;
import com.hyh.cstore.mapper.ProductMapper;
import com.hyh.cstore.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;
    @Override
    public void addToCart(Integer uid, Integer pid, Integer num, String username) {
        Cart res = cartMapper.findByUidAndPid(uid, pid);
        //当用户没有将该商品加入到某个用户购物车，则创建一个新购物车存改商品
        if(res == null){
            Cart cart = new Cart();
            Product product = productMapper.findById(pid);
            Long price = product.getPrice();
            cart.setPrice(price);
            cart.setPid(pid);
            cart.setNum(num);
            cart.setUid(uid);
            cart.setCreatedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedUser(username);
            cart.setModifiedTime(new Date());
            Integer integer = cartMapper.insertCart(cart);
            if(integer != 1){
                throw new InsertException("添加购物车错误");
            }
        }else{//如果购物车已经存在该商品，则更新商品数量
            num += res.getNum();
            Integer integer = cartMapper.updateNumByCid(num, res.getCid(), username, new Date());
            if(integer != 1){
                throw new UpdateException("修改商品数量发生错误");
            }
        }
    }

    @Override
    public List<CartVO> showCartListByUid(Integer uid) {
        return cartMapper.findCartListByUid(uid);
    }

    @Override
    public Integer addNumber(Integer cid, Integer uid, String username) {
        Cart cart = cartMapper.findByCid(cid);
        if (cart == null){
            throw  new CartNotFoundException("当前购物车记录不存在");
        }
        if(!cart.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问的数据");
        }
       Integer num = cart.getNum() + 1;
        Integer integer = cartMapper.updateNumByCid(num, cid, username, new Date());
        if(integer != 1){
            throw new UpdateException("添加商品失败");
        }
        return num;
    }

    @Override
    public List<CartVO> showCartListByCid(Integer[] cid, Integer uid) {
        List<CartVO> list = cartMapper.findCartListByCid(cid);
        Iterator<CartVO> iterator = list.iterator();
        while (iterator.hasNext()){
            CartVO cartVO = iterator.next();
            if(!cartVO.getUid().equals(uid)){
                iterator.remove();
            }
        }
        return list;
    }

    @Override
    public void deleteByCid(Integer uid, Integer cid) {
        Cart cart = cartMapper.findByCid(cid);
        if(cart == null){
            throw new CartNotFoundException("改购物车记录为空");
        }
        if (!cart.getUid().equals(uid)){
            throw new AccessDeniedException("非法操作");
        }
        Integer integer = cartMapper.deleteByCid(cid);
        if(integer != 1){
            throw new DeleteException("删除失败");
        }
    }

    @Override
    public Integer reduceNumber(Integer cid, Integer uid, String username) {
        Cart cart = cartMapper.findByCid(cid);
        if (cart == null){
            throw  new CartNotFoundException("当前购物车记录不存在");
        }
        if(!cart.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问的数据");
        }
        Integer num = cart.getNum() - 1;
        Integer integer = cartMapper.updateNumByCid(num, cid, username, new Date());
        if(integer != 1){
            throw new UpdateException("删除商品失败");
        }
        return num;
    }
}
