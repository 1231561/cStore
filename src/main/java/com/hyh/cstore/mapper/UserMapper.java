package com.hyh.cstore.mapper;

import com.hyh.cstore.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface UserMapper {
    /**
     * 插入用户
     * @param user 用户对象数据
     * @return 插入了几条数据（影响的行数）
     */
    Integer insert(User user);

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 找到则返回用户对象，找不到返回null
     */
    User findByName(String username);

    /**
     * 通过uid修改密码，并更新修改人和修改时间信息
     * @param uid
     * @param password
     * @param modifiedUser
     * @param modifiedTime
     * @return 影响几条记录
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 通过uid查找用户信息
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     * 修改用户信息
     * @param user 将user对象的属性传递给sql语句对应的字段
     * @return 影响行数
     */
    Integer changeInfoByUid(User user);

    /**
     * 修改用户头像
     * @param uid 用户id，通过session获取
     * @param avatar 用户头像地址，通过上传的文件获取
     * @param modifiedUser 通过session获取
     * @param modifiedTime 直接new获取
     * @return 影响数据库行数
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime")Date  modifiedTime);
}
