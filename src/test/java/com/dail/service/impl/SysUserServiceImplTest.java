package com.dail.service.impl;

import com.dail.entity.SysUser;
import com.dail.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Roger on 2016/12/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {

    @Autowired
    private SysUserService userService;

    @Test
    public void findByUsername() throws Exception {

    }

    @Test
    public void register() throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("roger");
        sysUser.setPassword("123456");
        sysUser.setEnabled(true);
        userService.register(sysUser);
    }
}