package com.dail.service.impl;

import com.dail.dao.PublicationMapper;
import com.dail.model.Publication;
import com.dail.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationMapper publicationMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return publicationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Publication record) {
        return publicationMapper.insert(record);
    }

    @Override
    public int insertSelective(Publication record) {
        return publicationMapper.insertSelective(record);
    }

    @Override
    public Publication selectByPrimaryKey(Integer id) {
        return publicationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Publication record) {
        return publicationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Publication record) {
        return publicationMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Publication record) {
        return publicationMapper.updateByPrimaryKey(record);
    }
}
