package com.hyh.cstore.mapper;

import com.hyh.cstore.entity.Address;

import java.util.Date;
import java.util.List;

/**收获地址接口*/
public interface AddressMapper {
    /**
     * 添加新收获地址
     * @param address 业务层将完整的地址信息传入
     * @return 影响的行数
     */
    Integer insert(Address address);

    /**
     * 统计一个用户的收获地址数量，不能大于20个
     * @param uid 从session中获取
     * @return 影响的行数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据地区号码得到地区名
     * @param code  从前端表单传过来的地址号码
     * @return 地区名
     */
    String findNameByCode(String code);

    /**
     * 根据用户id查询用户的所有收获地址信息
     * @param uid uid
     * @return 地址集合
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据地址id查询地址信息
     * @param aid 地址id
     * @return 地址对象
     */
    Address findByAid(Integer aid);

    /**
     * 将用户的所有收货地址都改为非默认的
     * @param uid 用户id
     * @return 影响行数
     */
    Integer updateNonDefaultByUid(Integer uid);

    /**
     * 根据地址id将某一条收货地址变为默认收货地址
     * @param aid 地址id
     * @param username 修改的用户
     * @param modifiedTime 修改时间
     * @return 影响的行数
     */
    Integer updateDefaultByAid(Integer aid, String username, Date modifiedTime);

    Integer deleteByAid(Integer aid);

    Address findLastModified(Integer uid);

}