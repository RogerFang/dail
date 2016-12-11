package com.dail.service;

import com.dail.model.News;
import com.github.pagehelper.PageInfo;

/**
 * Created by Roger on 2016/12/11.
 */
public interface NewsService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    PageInfo<News> page(int pageNumber, int pageSize);

    News selectByPrimaryKeyWithDetail(Integer id);

}
