package com.dail.service.impl;

import com.dail.entity.Department;
import com.dail.entity.People;
import com.dail.service.PeopleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Roger on 2016/12/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleServiceImplTest {

    @Autowired
    private PeopleService peopleService;

    @Test
    public void findById(){
        People people = peopleService.findById(1l);
        Department department = people.getDepartment();
        System.out.println(department.getId());
        Assert.assertNotNull(people.getDepartment());
    }
}