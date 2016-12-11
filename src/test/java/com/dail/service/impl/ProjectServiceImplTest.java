package com.dail.service.impl;

import com.dail.model.Project;
import com.dail.service.ProjectService;
import com.github.pagehelper.PageInfo;
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
public class ProjectServiceImplTest {

    @Autowired
    private ProjectService projectService;

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insertSelective() throws Exception {
        for (int i = 2; i< 11; i++){
            Project project = new Project();
            project.setTitle("test project "+i);
            project.setCreateTime(new Date());
            projectService.insertSelective(project);
        }
    }

    @Test
    public void selectByPrimaryKey() throws Exception {

    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void page() throws Exception {
        PageInfo<Project> page = projectService.page(1, 3);
        System.out.println(page.getList().size());
    }
}