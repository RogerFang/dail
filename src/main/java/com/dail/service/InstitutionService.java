package com.dail.service;

import com.dail.model.Institution;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
public interface InstitutionService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Institution record);

    Institution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Institution record);

    List<Institution> selectAll();

}
