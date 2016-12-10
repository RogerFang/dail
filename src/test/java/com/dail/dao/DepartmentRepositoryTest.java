package com.dail.dao;

import com.dail.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Roger on 2016/12/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void save(){
        Department department = new Department();
        department.setName("部门1");
        department.setDescription("部门1描述");
        department.setCreateTime(new Date());
        departmentRepository.save(department);
    }
}