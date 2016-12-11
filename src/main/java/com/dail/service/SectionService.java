package com.dail.service;

import com.dail.model.Section;

import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
public interface SectionService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Section record);

    Section selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Section record);

    List<Section> selectAll();

    List<Section> selectAllEnabled(Boolean enabled);
}
