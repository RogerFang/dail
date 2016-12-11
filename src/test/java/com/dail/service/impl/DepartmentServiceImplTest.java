package com.dail.service.impl;

import com.dail.model.Department;
import com.dail.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Roger on 2016/12/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void insertSelective() throws Exception {
        Department department = new Department();
        department.setName("部门2");
        department.setDescription("部门2描述");
        department.setCreateTime(new Date());
        departmentService.insertSelective(department);
    }
}