package com.dail.dao;

import com.dail.model.News;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);

    List<News> selectAllBase();

    List<News> selectAllBaseWithUser();

    List<News> selectAllBaseWithUserByUid(Integer userId);

    List<News> selectAllWithBlob();

    News selectByPrimaryKeyWithDetail(Integer id);

    List<News> searchAllBaseWithUser(String query);
}