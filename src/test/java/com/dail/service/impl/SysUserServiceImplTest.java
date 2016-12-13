package com.dail.service.impl;

import com.dail.model.SysUser;
import com.dail.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Roger on 2016/12/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SysUserServiceImplTest {
    @Autowired
    private SysUserService sysUserService;


    @Test
    public void register() throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("fanglong");
        sysUser.setPassword("123456");
        sysUser.setEnabled(true);
        sysUserService.register(sysUser);
    }
}