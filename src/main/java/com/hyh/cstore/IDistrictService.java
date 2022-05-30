package com.hyh.cstore;

import com.hyh.cstore.entity.District;

import java.util.List;

/**
 * 省市区列表业务接口
 */
public interface IDistrictService {
    /**
     * 返回根据父区域查询到的区域列表
     */
    List<District> getByParent(String parent);
}
