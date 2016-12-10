package com.dail.dao;

import com.dail.entity.Publication;
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
public class PublicationRepositoryTest {

    @Autowired
    private PublicationRepository publicationRepository;

    @Test
    public void findById(){

    }

    @Test
    public void save(){
        Publication publication = new Publication();
        publication.setApaText(" A Methodological Improvement for Topic Citation Analysis: Finding Contextual Topic Network on Full-Text Data");
        publication.setFullTextUrl("http://scholarwiki.indiana.edu/homepage/publication.html");
        publication.setCreateTime(new Date());
        // publication.setUid(1l);
        // publication.setPeopleId(1l);
        publicationRepository.save(publication);
    }
}