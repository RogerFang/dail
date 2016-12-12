package com.dail.service;

import com.dail.model.Publication;
import com.github.pagehelper.PageInfo;

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

    Publication selectByIdWithInfo(Integer id);

    PageInfo<Publication> page(int pageNumber, int pageSize);

    PageInfo<Publication> pageWithInfo(int pageNumber, int pageSize);
}
