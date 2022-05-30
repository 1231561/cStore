package com.hyh.cstore.controller;

import com.hyh.cstore.IAddressService;
import com.hyh.cstore.entity.Address;
import com.hyh.cstore.util.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**添加新收获地址的控制层*/
@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{
    @Autowired
    IAddressService service;
    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(HttpSession session, Address address){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        service.addNewAddress(uid, username, address);
        return new JsonResult<>(OK);
    }
    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<Address> list = service.getByUid(uid);
        return new JsonResult<>(OK,list);
    }
    @RequestMapping("{id}/set_default")
    public JsonResult<Void> setDefault(HttpSession session,
                                       @PathVariable("id") Integer aid){
        service.setDefault(aid,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
    @RequestMapping("{aid}/deleteAddress")
    public JsonResult<Void> deleteAddress(HttpSession session,
                                       @PathVariable("aid") Integer aid){
        service.deleteAddress(getUidFromSession(session), aid, getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
