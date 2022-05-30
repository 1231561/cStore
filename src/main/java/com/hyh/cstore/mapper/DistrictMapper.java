package com.hyh.cstore.mapper;

import com.hyh.cstore.entity.District;

import java.util.List;

/**
 * 省市区列表接口
 */
public interface DistrictMapper {
    /**
     * 返回根据父区域查询到的区域列表
     */
    List<District> findByParent(String parent);

}
