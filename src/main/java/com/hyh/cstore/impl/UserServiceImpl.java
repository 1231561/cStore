package com.hyh.cstore.impl;

import com.hyh.cstore.IUserService;
import com.hyh.cstore.entity.User;
import com.hyh.cstore.ex.*;
import com.hyh.cstore.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void reg(User user) {
        User res = userMapper.findByName(user.getUsername());
        //用户名存在
        if(res != null){
            throw new UsernameDuplicateException("该用户名已存在");
        }
        //将注册用户必要的用户信息填充完整

        //使用md5算法给用户密码加密:加密形式:盐值+password+盐值三次加密
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase(Locale.ROOT);
        String md5Password = getMd5Password(oldPassword, salt);
        //保存加密后的密码
        user.setPassword(md5Password);
        //必须保存盐值，后面登录时需要根据数据库表里的盐值加密码验证登录
        user.setSalt(salt);

        //用户的注销状态
        user.setIsDelete(0);
        Date date = new Date();
        //四个用户注册日志
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        Integer insert = userMapper.insert(user);
        //插入用户数据失败
        if(insert != 1){
            throw new InsertException("注册用户时出现了些问题");
        }
    }
    /** 用户登录业务*/
    @Override
    public User login(String username, String password) {
        User res = userMapper.findByName(username);
        if(res == null){
            throw new UserNotFoundException("当前用户不存在");
        }
        //检测用户注销状态
        Integer isDelete = res.getIsDelete();
        if(isDelete == 1){
            throw new UserNotFoundException("当前用户不存在");
        }
        //对输入的密码进行MD5三次加密，然后和原密码比较
        String userPassword = res.getPassword();
        String salt = res.getSalt();
        String logPassword = getMd5Password(password, salt);
        if(!userPassword.equals(logPassword)){
            throw new PasswordNotMatchException("密码错误");
        }
        User user = new User();
        user.setUid(res.getUid());
        user.setUsername(res.getUsername());
        user.setAvatar(res.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User res = userMapper.findByUid(uid);
        if(res == null){
            throw new UserNotFoundException("用户不存在");
        }
        if(res.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }
        String salt = res.getSalt();
        String md5OldPassword = getMd5Password(oldPassword, salt);
        if(!res.getPassword().equals(md5OldPassword)){
            throw new PasswordNotMatchException("原密码错误");
        }
        String md5NewPassword = getMd5Password(newPassword, salt);
        Integer integer = userMapper.updatePasswordByUid(uid, md5NewPassword, username, new Date());
        if(integer != 1){
            throw new UpdateException("修改密码时发生了未知错误");
        }
    }

    @Override
    public void changeInfoByUid(Integer uid, String username, User user) {
        User res = userMapper.findByUid(uid);
        if(res == null || res.getIsDelete() == 1){
            throw new UserNotFoundException("用户没找到");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer integer = userMapper.changeInfoByUid(user);
        if(integer != 1){
            throw new UpdateException("资料修改时出现未知错误");
        }
    }

    @Override
    public User findUserByUid(Integer uid) {
        User res = userMapper.findByUid(uid);
        if(res == null || res.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }
        User user = new User();
        user.setUsername(res.getUsername());
        user.setPhone(res.getPhone());
        user.setEmail(res.getEmail());
        user.setGender(res.getGender());
        return user;
    }

    @Override
    public void updateAvatar(Integer uid, String avatar, String username) {
        User res = userMapper.findByUid(uid);
        if(res == null || res.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }
        Integer integer = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if(integer != 1){
            throw new UpdateException("头像修改时出现未知问题");
        }
    }

    public String getMd5Password(String password, String salt){
        for(int i = 0; i < 3; i++){
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
