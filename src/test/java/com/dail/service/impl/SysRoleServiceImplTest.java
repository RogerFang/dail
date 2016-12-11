package com.dail.service.impl;

import com.dail.model.SysRole;
import com.dail.service.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * Created by Roger on 2016/12/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SysRoleServiceImplTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void selectAll() throws Exception {
        List<SysRole> roles = sysRoleService.selectAll();
    }

    @Test
    public void selectByName() throws Exception {

    }

    @Test
    public void selectByUserId() throws Exception {
        Set<SysRole> roles = sysRoleService.selectByUserId(1);
        System.out.println(roles.size());
    }

}