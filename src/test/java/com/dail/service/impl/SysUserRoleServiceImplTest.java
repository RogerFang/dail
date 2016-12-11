package com.dail.service.impl;

import com.dail.service.SysUserRoleService;
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
public class SysUserRoleServiceImplTest {

    @Autowired
    private SysUserRoleService userRoleService;

    @Test
    public void deleteByUserId() throws Exception {
        userRoleService.deleteByUserId(1);
    }

    @Test
    public void insertSelective() throws Exception {

    }

    @Test
    public void changeToAdmin() throws Exception {
        userRoleService.changeToAdmin(1);
    }

    @Test
    public void changeToGeneral() throws Exception {
        userRoleService.changeToGeneral(1);
    }
}