package com.dail.dao;

import com.dail.model.Section;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Section record);

    int insertSelective(Section record);

    Section selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);

    List<Section> selectAll();

    List<Section> selectAllEnabled(Boolean enabled);
}