package com.dail.service.impl;

import com.dail.dao.NewsMapper;
import com.dail.model.News;
import com.dail.service.NewsService;
import com.dail.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class NewsServiceImpl implements NewsService {

    private static final int MAX_LENGTH = 600;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return newsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(News record) {
        return newsMapper.insertSelective(record);
    }

    @Override
    public News selectByPrimaryKey(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(News record) {
        return newsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<News> page(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<News> newsList = newsMapper.selectAllBaseWithUser();
        return new PageInfo<>(newsList);
    }

    @Override
    public PageInfo<News> pageByUid(int pageNumber, int pageSize, Integer uid) {
        PageHelper.startPage(pageNumber, pageSize);
        List<News> newsList = newsMapper.selectAllBaseWithUserByUid(uid);
        return new PageInfo<>(newsList);
    }

    @Override
    public News selectByPrimaryKeyWithDetail(Integer id) {
        return newsMapper.selectByPrimaryKeyWithDetail(id);
    }

    @Override
    public void insert(News news) {
        Date now = new Date();
        news.setLastModifiedTime(now);
        news.setCreateTime(now);
        news.setDescription(StrUtil.getShortContent(news.getContent(), MAX_LENGTH));
        newsMapper.insertSelective(news);
    }

    @Override
    public void update(News news) {
        news.setLastModifiedTime(new Date());
        news.setDescription(StrUtil.getShortContent(news.getContent(), MAX_LENGTH));
        newsMapper.updateByPrimaryKeySelective(news);
    }

    @Override
    public PageInfo<News> search(int pageNumber, int pageSize, String query) {
        PageHelper.startPage(pageNumber, pageSize);
        List<News> newsList = newsMapper.searchAllBaseWithUser(StrUtil.filtersql(query));
        return new PageInfo<>(newsList);
    }
}
