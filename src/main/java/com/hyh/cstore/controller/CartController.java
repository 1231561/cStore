package com.hyh.cstore.controller;

import com.hyh.cstore.ICartService;
import com.hyh.cstore.util.JsonResult;
import com.hyh.cstore.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController{
    @Autowired
    ICartService cartService;

    /**
     * 增加商品数量
     * @param session 会话
     * @param pid 商品id，通过url获取
     * @param num 通过前端的num标签获取
     * @return
     */
    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(HttpSession session, Integer pid, Integer num){
        cartService.addToCart(getUidFromSession(session), pid, num, getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
    @RequestMapping({"","/"})
    public JsonResult<List<CartVO>> showCartListByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<CartVO> list = cartService.showCartListByUid(uid);
        return new JsonResult<>(OK,list);
    }
    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(HttpSession session,
                                      @PathVariable("cid") Integer cid){
        Integer num= cartService.addNumber(cid, getUidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK,num);
    }

    @RequestMapping("show_order")
    public JsonResult<List<CartVO>> showCartListByCid(HttpSession session, Integer[] cids){
        List<CartVO> list = cartService.showCartListByCid(cids, getUidFromSession(session));
        return new JsonResult<>(OK,list);
    }
}
