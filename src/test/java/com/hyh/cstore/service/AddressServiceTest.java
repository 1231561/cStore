package com.hyh.cstore.service;

import com.hyh.cstore.IAddressService;
import com.hyh.cstore.entity.Address;
import com.hyh.cstore.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class AddressServiceTest {
    @Autowired
    IAddressService service;
    @Test
    void addNewAddress(){
        Address address = new Address();
        address.setName("小明");
        address.setAddress("北京");
        address.setPhone("13923412312");
        service.addNewAddress(1,"张三",address);

    }
    @Test
    public void setDefault(){

    }
    @Test
    public void delete() {
        try {
            Integer aid = 10;
            Integer uid = 4;
            String username = "明明";
            service.deleteAddress(uid, aid, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

}
