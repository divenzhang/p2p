package com.eloan.uiweb.test;

import com.eloan.business.domain.Userinfo;
import com.eloan.business.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-uiweb.xml")
public class test {
    @Autowired
    private IUserService userService;
    @Test
    public void  testUser(){
        Userinfo userinfo=userService.get(3L);
        System.out.println(userinfo.toString());
    }
}
