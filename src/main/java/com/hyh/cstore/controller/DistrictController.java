package com.hyh.cstore.controller;

import com.hyh.cstore.IDistrictService;
import com.hyh.cstore.entity.District;
import com.hyh.cstore.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{
    @Autowired
    IDistrictService service;
    @RequestMapping({"", "/"})
    JsonResult<List<District>> getByParent(String parent){
        List<District> list = service.getByParent(parent);
        return new JsonResult<>(OK,list);
    }
}
