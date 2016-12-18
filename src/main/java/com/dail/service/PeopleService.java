package com.dail.service;

import com.dail.model.People;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by Roger on 2016/12/10.
 */
public interface PeopleService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(People record);

    People selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(People record);

    PageInfo<People> pageWithInfo(int pageNumber, int pageSize);

    PageInfo<People> pageWithInfoUser(int pageNumber, int pageSize);

    PageInfo<People> pageWithInfoUserLike(int pageNumber, int pageSize, String query);

    People selectByIdWithDetail(Integer id);

    People selectByIdWithInfoUser(Integer id);

    List<People> selectAllNotParticipants(List<People> list);

    List<People> selectAll();

    void update(People record);

    void insert(People record);

    PageInfo<People> search(int pageNumber, int pageSize, String query);
}
