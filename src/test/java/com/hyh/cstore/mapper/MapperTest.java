package com.hyh.cstore.mapper;

import com.hyh.cstore.entity.Address;
import com.hyh.cstore.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Test
    void insert(){
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        System.out.println(userMapper.insert(user));
    }
    @Test
    void select(){
        System.out.println(userMapper.findByName("张三"));
    }
    @Test
    void modified(){
        System.out.println(userMapper.findByUid(2));
        System.out.println(userMapper.updatePasswordByUid(3, "654321", "李五", new Date()));
    }
    @Test
    void updateAvatar(){
        System.out.println(userMapper.updateAvatarByUid(3, "/img/header", "小米", new Date()));
    }
    @Test
    public void insertAddress() {
        Address address = new Address();
        address.setUid(4);
        address.setName("admin");
        address.setPhone("17858802974");
        address.setAddress("雁塔区小寨赛格");
        Integer rows = addressMapper.insert(address);
        System.out.println("rows=" + rows);
    }

    @Test
    public void countByUid() {
        Integer uid = 4;
        Integer count = addressMapper.countByUid(uid);
        System.out.println("count=" + count);
    }
}
