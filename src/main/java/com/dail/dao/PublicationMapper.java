package com.dail.dao;

import com.dail.model.Publication;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Publication record);

    int insertSelective(Publication record);

    Publication selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Publication record);

    int updateByPrimaryKeyWithBLOBs(Publication record);

    int updateByPrimaryKey(Publication record);

    List<Publication> selectByPeopleId(Integer id);

    Publication selectByIdWithInfo(Integer id);

    List<Publication> selectAll();

    List<Publication> selectAllWithInfo();
}