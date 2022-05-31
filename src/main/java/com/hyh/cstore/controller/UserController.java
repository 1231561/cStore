package com.hyh.cstore.controller;

import com.hyh.cstore.IUserService;
import com.hyh.cstore.controller.ex.*;
import com.hyh.cstore.entity.User;
import com.hyh.cstore.ex.InsertException;
import com.hyh.cstore.ex.ServiceException;
import com.hyh.cstore.ex.UsernameDuplicateException;
import com.hyh.cstore.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController{
    @Autowired
    IUserService service;

    /**
     *用户注册
     * @param user （约定大于配置）将实体类User类型作为参数，springboot会将前端的url地址中的参数名和实体类的属性名进行比较，比较结果相同的实体类属性被注入参数值
     * @return 返回给前端的结果集
     */
    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user){
        service.reg(user);
        return new JsonResult<>(OK);
    }

    /**
     * 用户登录
     *参数为非实体类，springboot将url的参数名和方法参数名进行比较，相同名字的参数被注入值
     * @return 返回给前端的结果集
     */
    @RequestMapping("/login")
    //泛型设置为User，表示data为user对象数据，返回JsonResult的实例给前端，包含状态码和user对象，和状态信息。
    public JsonResult<User> login(String username, String password, HttpSession session){
        User user = service.login(username, password);
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());
        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        //构造方法传递user对象的值及状态码
        return new JsonResult<>(OK,user);
    }

    /**
     * 修改密码控制器方法，参数名和前端的表单的name属性名一致。
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param session 通过会话获取修改密码人的信息
     * @return 给前端返回结果集
     */
    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        service.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    /**
     * 处理访问用户资料页面的请求
     * @param session
     * @return
     */
    @RequestMapping("/get_by_id")
    public JsonResult<User> getInfoByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        User res = service.findUserByUid(uid);
        return new JsonResult<User>(OK,res);
    }

    /**
     * 处理修改用户资料的请求
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        service.changeInfoByUid(uid, username, user);
        return new JsonResult<>(OK);
    }
    public static final int MAX_SIZE = 10*1024*1024;
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    @RequestMapping("/update_avatar")
    public JsonResult<String> updateAvatar(HttpSession session, MultipartFile file){
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
        if (file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize() > MAX_SIZE){
            throw new FileSizeException("文件尺寸不能大于10M");
        }
        if(!AVATAR_TYPE.contains(file.getContentType())){
            throw new FileTypeException("文件类型不匹配");
        }
        //在工程项目下创建upload目录，保存上传头像文件
        String parent = session.getServletContext().getRealPath("upload");
        File dir = new File(parent);
        if(!dir.exists()){
            dir.mkdir();
        }
        System.out.println(parent);
        //在parent目录下创建空文件，文件名为uuid加上传文件后缀名
        int index = file.getOriginalFilename().indexOf(".");
        String suffix = file.getOriginalFilename().substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        File path = new File(dir, filename);
        //将文件数据传输给文件
        try {
            file.transferTo(path);
        } catch (IllegalStateException e) {
            throw new FileUploadIOException("文件状态异常，或已被删除");
        } catch (IOException e) {
            throw new FileUploadIOException("上传文件时读写异常");
        }
        //保存服务器头像文件的路径
        String avatar = "/upload/" + filename;
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        service.updateAvatar(uid, avatar, username);
        return new JsonResult<String>(OK,avatar);
    }
}
