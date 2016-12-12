package com.dail.dao;

import com.dail.model.Institution;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Institution record);

    int insertSelective(Institution record);

    Institution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Institution record);

    int updateByPrimaryKey(Institution record);

    List<Institution> selectAll();
}