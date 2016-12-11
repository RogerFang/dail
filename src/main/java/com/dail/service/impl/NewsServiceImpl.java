package com.dail.service.impl;

import com.dail.dao.NewsMapper;
import com.dail.model.News;
import com.dail.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class NewsServiceImpl implements NewsService {

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
        PageHelper.startPage(pageNumber, pageSize, "id desc");
        List<News> newsList = newsMapper.selectAllBase();
        return new PageInfo<>(newsList);
    }
}
