package com.hyh.cstore.mapper;

import com.hyh.cstore.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistrictMapperTest {
    @Autowired
    DistrictMapper districtMapper;
    @Test
    void findByDistrict(){
        List<District> list = districtMapper.findByParent("110100");
        for(District l : list){
            System.out.println(l);
        }
    }
}
