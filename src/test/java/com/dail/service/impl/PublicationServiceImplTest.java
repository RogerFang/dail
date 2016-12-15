package com.dail.service.impl;

import com.dail.dao.PublicationMapper;
import com.dail.model.Publication;
import com.dail.service.PublicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PublicationServiceImplTest {

    @Autowired
    private PublicationService publicationService;
    @Autowired
    private PublicationMapper publicationMapper;

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insertSelective() throws Exception {
        Publication publication = new Publication();
        publication.setApaText(" A Methodological Improvement for Topic Citation Analysis: Finding Contextual Topic Network on Full-Text Data");
        publication.setFullTextUrl("http://scholarwiki.indiana.edu/homepage/publication.html");
        publication.setCreateTime(new Date());
        publicationService.insertSelective(publication);
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKeyWithBLOBs() throws Exception {

    }


    @Test
    public void selectByPeopleId(){
        List<Publication> list = publicationMapper.selectByPeopleId(3);
        System.out.println(list.size());
    }
}