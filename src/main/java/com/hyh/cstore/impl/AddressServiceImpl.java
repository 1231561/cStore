package com.hyh.cstore.impl;

import com.hyh.cstore.IAddressService;
import com.hyh.cstore.entity.Address;
import com.hyh.cstore.entity.User;
import com.hyh.cstore.ex.*;
import com.hyh.cstore.mapper.AddressMapper;
import com.hyh.cstore.mapper.DistrictMapper;
import com.hyh.cstore.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    UserMapper userMapper;
    @Value("${address.count-max}")
    private Integer maxCount;
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        User user = userMapper.findByUid(uid);
        if(user == null){
            throw new UserNotFoundException("当前用户不存在");
        }
        Integer count = addressMapper.countByUid(uid);
        if(count >= maxCount){
            throw new AddressCountLimitException("收货地址数超过最大上限");
        }
        //如果是第一条新添地址，则设置为默认地址
        address.setIsDefault(count == 0 ? 1 : 0);
        address.setUid(uid);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        //根据地区码获得地区名，并保存到地址对象中
        String provinceName = addressMapper.findNameByCode(address.getProvinceCode());
        String cityName = addressMapper.findNameByCode(address.getCityCode());
        String areaName = addressMapper.findNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);
        //将地址添加到数据库
        Integer insert = addressMapper.insert(address);
        if(insert != 1){
            throw new UpdateException("添加新地址出现了未知的异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        User res = userMapper.findByUid(uid);
        if(res == null){
            throw new UserNotFoundException("该用户不存在");
        }
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list){
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address res = addressMapper.findByAid(aid);
        if (res == null){
            throw new AddressNotFoundException("当前要修改的地址不存在");
        }
        if(!res.getUid().equals(uid)){
            throw new AccessDeniedException("这条地址不属于当前用户");
        }
        Integer integer1 = addressMapper.updateNonDefaultByUid(uid);
        if (integer1 == 0){
            throw new UpdateException("更新数据时出现异常");
        }
        Integer integer2 = addressMapper.updateDefaultByAid(aid, username, new Date());
        if(integer2 != 1){
            throw new UpdateException("更新数据时出现异常");
        }
    }

    @Override
    public void deleteAddress(Integer uid, Integer aid, String username) {
        Address res = addressMapper.findByAid(aid);
        if(res == null){
            throw new AddressNotFoundException("该地址不存在");
        }
        if(!res.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer delete = addressMapper.deleteByAid(aid);
        if (delete != 1){
            throw new DeleteException("删除时发生异常");
        }
        //删除的是非默认地址
        if(res.getIsDefault() == 0){
            return;
        }
        //删除唯一一条地址(默认地址)
        if(addressMapper.countByUid(uid) == 0){
            return;
        }
        //删除默认地址，且还有非默认地址
        Address lastModified = addressMapper.findLastModified(uid);
        Integer update = addressMapper.updateDefaultByAid(lastModified.getAid(), username, new Date());
        if(update != 1){
            throw new UpdateException("更新默认地址时发生错误");
        }

    }

    @Override
    public Address getAddress(Integer aid, Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if (address == null){
            throw new AddressNotFoundException("当前地址不存在");
        }
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }
}
