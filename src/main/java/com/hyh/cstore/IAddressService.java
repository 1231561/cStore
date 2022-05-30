package com.hyh.cstore;

import com.hyh.cstore.entity.Address;

import java.util.List;

/**地址业务层接口*/
public interface IAddressService {
    /**
     * 添加新的收货地址
     * @param uid 从session中传递过来
     * @param username 从session中传递过来
     * @param address 从表单数据获取注入address对象
     */
    void addNewAddress(Integer uid, String username, Address address);

    List<Address> getByUid(Integer uid);

    void setDefault(Integer aid, Integer uid, String username);

    /**
     * 删除收货地址
     * @param uid 用户id
     * @param aid 地址id
     * @param username 删除默认地址后，重新设置默认地址需要用到
     */
    void deleteAddress(Integer uid, Integer aid, String username);

    Address getAddress(Integer aid, Integer uid);

}
