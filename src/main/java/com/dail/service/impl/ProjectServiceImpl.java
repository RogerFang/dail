package com.dail.service.impl;

import com.dail.dao.ProjectMapper;
import com.dail.model.Project;
import com.dail.model.ProjectPeopleKey;
import com.dail.service.ProjectPeopleService;
import com.dail.service.ProjectService;
import com.dail.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    private static int MAX_LENGTH = 800;

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectPeopleService projectPeopleService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return projectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Project record) {
        record.setDescription(StrUtil.getShortContent(record.getContent(), MAX_LENGTH));
        return projectMapper.insertSelective(record);
    }

    @Override
    public Project selectByPrimaryKey(Integer id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Project record) {
        return projectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<Project> page(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Project> projects = projectMapper.selectAllBaseInfo();
        return new PageInfo<>(projects);
    }

    @Override
    public PageInfo<Project> pageByUid(Integer uid, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Project> projects = projectMapper.selectAllBaseInfoByUid(uid);
        return new PageInfo<>(projects);
    }

    @Override
    public Project selectByIdWithInfo(Integer id) {
        return projectMapper.selectByIdWithInfo(id);
    }

    @Override
    public Project selectByIdWithDetail(Integer id) {
        return projectMapper.selectByIdWithDetail(id);
    }

    @Transactional
    @Override
    public void update(Project record) {
        record.setDescription(StrUtil.getShortContent(record.getContent(), MAX_LENGTH));
        record.setLastModifiedTime(new Date());
        projectMapper.updateByPrimaryKeySelective(record);
        List<Integer> participantIds = record.getParticipantIds();
        if (participantIds != null && participantIds.size() > 0){
            projectPeopleService.deleteByProjectId(record.getId());
            for (Integer pid: participantIds){
                ProjectPeopleKey key = new ProjectPeopleKey();
                key.setPeopleId(pid);
                key.setProjectId(record.getId());
                projectPeopleService.insert(key);
            }
        }
    }

    @Transactional
    @Override
    public void insert(Project record) {
        Date now = new Date();
        record.setLastModifiedTime(now);
        record.setCreateTime(now);
        record.setDescription(StrUtil.getShortContent(record.getContent(), MAX_LENGTH));
        projectMapper.insertSelective(record);
        List<Integer> participantIds = record.getParticipantIds();
        if (participantIds != null && participantIds.size() > 0){
            for (Integer pid: participantIds){
                ProjectPeopleKey key = new ProjectPeopleKey();
                key.setPeopleId(pid);
                key.setProjectId(record.getId());
                projectPeopleService.insert(key);
            }
        }
    }

    @Override
    public PageInfo<Project> search(int pageNumber, int pageSize, String query) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Project> projects = projectMapper.searchAllBaseInfo(StrUtil.filtersql(query));
        return new PageInfo<>(projects);
    }
}
