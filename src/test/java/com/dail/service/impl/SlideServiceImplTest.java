package com.dail.service.impl;

import com.dail.model.Slide;
import com.dail.service.SlideService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SlideServiceImplTest {

    @Autowired
    private SlideService slideService;

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insertSelective() throws Exception {

    }

    @Test
    public void selectByPrimaryKey() throws Exception {

    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void selectAllEnabled() throws Exception {
        List<Slide> slides = slideService.selectAllEnabled(Boolean.TRUE);
        System.out.println(slides.size());
    }

    @Test
    public void selectAll() throws Exception {
        List<Slide> slides = slideService.selectAll();
        System.out.println(slides.size());
    }

}