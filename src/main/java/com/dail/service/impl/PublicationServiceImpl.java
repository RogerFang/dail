package com.dail.service.impl;

import com.dail.dao.PublicationMapper;
import com.dail.model.Publication;
import com.dail.service.PublicationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Publication selectByIdWithInfo(Integer id) {
        return publicationMapper.selectByIdWithInfo(id);
    }

    @Override
    public PageInfo<Publication> page(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Publication> publications= publicationMapper.selectAll();
        return new PageInfo<>(publications);
    }

    @Override
    public PageInfo<Publication> pageWithInfo(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Publication> publications = publicationMapper.selectAllWithInfo();
        return new PageInfo<>(publications);
    }
}
