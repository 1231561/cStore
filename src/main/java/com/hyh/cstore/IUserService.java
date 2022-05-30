package com.hyh.cstore;

import com.hyh.cstore.entity.User;

/**处理用户数据的业务层接口*/
public interface IUserService {
    /**
     * 用户注册
     * @param user 传入一个用户类
     */
    void reg(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return user对象，供创建session使用
     */
    User login(String username, String password);

    /**
     * 修改密码
     * @param uid 通过session对象获取
     * @param username 通过session对象获取
     * @param oldPassword 通过前端请求获取
     * @param newPassword 通过前端请求获取
     */
    void changePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * 修改用户资料
     * @param uid
     * @param username
     * @param user
     */
    void changeInfoByUid(Integer uid, String username, User user);

    /**
     * 查询用户数据，用来回显给用户资料页面
     * @param uid
     * @return
     */
    User findUserByUid(Integer uid);

    /**
     * 更新头像业务方法
     * @param uid 通过会话获得
     * @param avatar 通过上传的文件信息获得
     * @param username 通过session获得
     */
    void updateAvatar(Integer uid, String avatar, String username);
}
