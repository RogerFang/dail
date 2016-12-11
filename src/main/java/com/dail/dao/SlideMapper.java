package com.dail.dao;

import com.dail.model.Slide;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Slide record);

    int insertSelective(Slide record);

    Slide selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Slide record);

    int updateByPrimaryKey(Slide record);

    List<Slide> selectAllEnabled(Boolean enabled);

    List<Slide> selectAll();
}