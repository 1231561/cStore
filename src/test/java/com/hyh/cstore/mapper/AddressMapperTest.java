package com.hyh.cstore.mapper;

import com.hyh.cstore.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
@SpringBootTest
public class AddressMapperTest {
    @Autowired
    AddressMapper mapper;
    @Test
    void findByAid1(){
        System.out.println(mapper.findByAid(12));

    }
    @Test
    void updateNonDefaultByUid1(){
        mapper.updateNonDefaultByUid(4);
    }
    @Test
    void updateDefaultByAid(){
        mapper.updateDefaultByAid(11,"张三",new Date());
    }

    @Test
    public void deleteByAid() {
        Integer aid = 12;
        Integer rows = mapper.deleteByAid(aid);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findLastModified() {
        Integer uid = 4;
        Address result = mapper.findLastModified(uid);
        System.out.println(result);
    }
}
