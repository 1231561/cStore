package com.hyh.cstore.controller;

import com.hyh.cstore.controller.ex.*;
import com.hyh.cstore.ex.*;
import com.hyh.cstore.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    public static final Integer OK = 200;

    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if(e instanceof UsernameDuplicateException){
            result.setState(4000);
        }else if(e instanceof UserNotFoundException){
            result.setState(4001);
        }else if(e instanceof PasswordNotMatchException){
            result.setState(4002);
        }else if(e instanceof UpdateException){
            result.setState(4003);
        }else if(e instanceof AddressCountLimitException){
            result.setState(4004);
        }else if(e instanceof AccessDeniedException){
            result.setState(4005);
        }else if(e instanceof AddressNotFoundException){
            result.setState(4006);
        }else if(e instanceof ProductNotFoundException){
            result.setState(4007);
        }else if(e instanceof CartNotFoundException){
            result.setState(4008);
        }else if(e instanceof OrderItemNotFoundException){
            result.setState(4009);
        }else if(e instanceof InsertException){
            result.setState(5000);
        }else if(e instanceof DeleteException){
            result.setState(5001);
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
        }else if (e instanceof FileSizeException) {
            result.setState(6001);
        }else if (e instanceof FileTypeException) {
            result.setState(6002);
        }else if (e instanceof FileStateException) {
            result.setState(6003);
        }else if (e instanceof FileUploadIOException) {
            result.setState(6004);
        }
        return result;
    }
    protected final Integer getUidFromSession(HttpSession session){
        Integer uid = (Integer) session.getAttribute("uid");
        return uid;
    }
    protected final String getUsernameFromSession(HttpSession session){
        String username = (String) session.getAttribute("username");
        return username;
    }

}
