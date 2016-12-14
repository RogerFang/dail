package com.dail.dao;

import com.dail.model.People;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(People record);

    int insertSelective(People record);

    People selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKeyWithBLOBs(People record);

    int updateByPrimaryKey(People record);

    List<People> selectAllBase();

    List<People> selectAllBaseWithInfo();

    List<People> selectAllBaseWithInfoUser();

    List<People> selectAllBaseWithInfoUserLike(@Param("query") String query);

    People selectByIdWithDetail(Integer id);

    People selectByIdWithInfo(Integer id);

    People selectByIdWithInfoUser(Integer id);

    List<People> selectAllNotParticipants(List<Integer> list);
}