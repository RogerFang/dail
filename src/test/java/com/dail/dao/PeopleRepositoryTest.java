package com.dail.dao;

import com.dail.entity.Department;
import com.dail.entity.People;
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

    @Test
    public void save(){
        People people = new People();
        people.setName("liuxiaozhong");
        people.setDescription("Dr. Xiaozhong Liu is an Assistant Professor at Department of Information and Library Science, School of Informatics and Computing, Indiana University Bloomington. His research interests include information retrieval, natural language processing, text/graph mining, digital library, metadata, and human computing.");
        people.setPosition("Professor");
        people.setSequence(1);
        peopleRepository.save(people);
    }

    @Test
    public void findById(){
        People people = peopleRepository.findOne(1l);
        Department department = people.getDepartment();
        System.out.println(department.getId());
        Assert.assertNotNull(people.getDepartment());
    }
}