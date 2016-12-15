package com.dail.service.impl;

import com.dail.model.News;
import com.dail.service.NewsService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Roger on 2016/12/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsServiceImplTest {

    @Autowired
    private NewsService newsService;

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insertSelective() throws Exception {
        for (int i = 1; i < 10; i++){
            News news = new News();
            news.setImgUrl("/assets/img/news.png");
            news.setTitle("This is a news " + i);
            news.setDescription("News Description: It's about something...");
            news.setCreateTime(new Date());
            newsService.insertSelective(news);
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
        PageInfo<News> pageInfo = newsService.page(2, 3);
        System.out.println(pageInfo.getList().size());
        System.out.println(pageInfo.getEndRow());
        System.out.println(pageInfo.getFirstPage());
        System.out.println(pageInfo.getLastPage());
        for (int i: pageInfo.getNavigatepageNums()){
            System.out.print(i+",");
        }
        System.out.println();
        System.out.println(pageInfo.getNavigatePages());
        System.out.println(pageInfo.getPageNum());
        System.out.println(pageInfo.getPages());
        System.out.println(pageInfo.getStartRow());
        System.out.println(pageInfo.getTotal());
    }

    @Test
    public void selectByPrimaryKeyWithUser() throws Exception {
        News news = newsService.selectByPrimaryKeyWithDetail(1);
        System.out.println(news.getSysUser().getUsername());
    }

}