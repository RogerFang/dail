package com.dail.service;

import com.dail.model.Publication;

/**
 * Created by Roger on 2016/12/11.
 */
public interface PublicationService {

    int deleteByPrimaryKey(Integer id);

    int insert(Publication record);

    int insertSelective(Publication record);

    Publication selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Publication record);

    int updateByPrimaryKeyWithBLOBs(Publication record);

    int updateByPrimaryKey(Publication record);
}
