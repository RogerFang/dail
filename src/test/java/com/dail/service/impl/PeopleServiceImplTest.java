package com.dail.service.impl;

import com.dail.model.People;
import com.dail.service.PeopleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Roger on 2016/12/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PeopleServiceImplTest {

    @Autowired
    private PeopleService peopleService;

    @Test
    public void selectById() throws Exception {
        People people = peopleService.selectByPrimaryKey(1);
    }

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insertSelective() throws Exception {
        People people = new People();
        people.setName("dingying");
        people.setPosition("Professor");
        people.setSequence(1);
        peopleService.insertSelective(people);
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKeyWithBLOBs() throws Exception {

    }
}