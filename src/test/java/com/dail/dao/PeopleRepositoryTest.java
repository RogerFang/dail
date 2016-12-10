package com.dail.dao;

import com.dail.entity.Department;
import com.dail.entity.People;
import org.hibernate.Hibernate;
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
public class PeopleRepositoryTest {
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void save(){
        People people = new People();
        people.setName("dingying");
        people.setPosition("Professor");
        people.setSequence(1);
        people = peopleRepository.save(people);
        System.out.println(people.getId());
    }

    @Test
    public void findById(){
        People people = peopleRepository.findOne(1l);
        //Department department = people.getDepartment();
        //Hibernate.initialize(department);
        //System.out.println(department.getId());
        //Assert.assertNotNull(people.getDepartment());

        System.out.println(people.getDepartment().getName());
    }

    @Test
    public void update(){
        //peopleRepository.(2l, 1l);
        People people = peopleRepository.findOne(1l);
        people.setEmail("xxxx@xxx");
        //people.getDepartment().getId();
        //Department department = departmentRepository.findOne(1l);
        //people.setDepartment(department);
        peopleRepository.save(people);
    }
}