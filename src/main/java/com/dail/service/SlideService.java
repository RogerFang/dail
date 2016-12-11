package com.dail.service;

import com.dail.model.Slide;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
public interface SlideService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Slide record);

    Slide selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Slide record);

    List<Slide> selectAllEnabled(Boolean enabled);

    List<Slide> selectAll();
}
