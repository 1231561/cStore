package com.hyh.cstore.impl;

import com.hyh.cstore.IDistrictService;
import com.hyh.cstore.entity.District;
import com.hyh.cstore.mapper.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    DistrictMapper districtMapper;
    @Override
    public List<District> getByParent(String parent) {
        List<District> districtList = districtMapper.findByParent(parent);
        for(District l : districtList){
            l.setId(null);
            l.setParent(null);
        }
        return districtList;
    }
}
