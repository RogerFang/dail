package com.dail.service.impl;

import com.dail.dao.ProjectMapper;
import com.dail.model.Project;
import com.dail.model.ProjectDirectionKey;
import com.dail.model.ProjectPeopleKey;
import com.dail.service.ProjectDirectionService;
import com.dail.service.ProjectPeopleService;
import com.dail.service.ProjectService;
import com.dail.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private ProjectDirectionService projectDirectionService;

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
        projectMapper.updateByPrimaryKeySelective(record);
        List<Integer> participantIds = record.getParticipantIds();
        List<Integer> directionIds = record.getDirectionIds();
        if (participantIds != null && participantIds.size() > 0){
            projectPeopleService.deleteByProjectId(record.getId());
            for (Integer pid: participantIds){
                ProjectPeopleKey key = new ProjectPeopleKey();
                key.setPeopleId(pid);
                key.setProjectId(record.getId());
                projectPeopleService.insert(key);
            }
        }
        if (directionIds != null && directionIds.size() > 0){
            projectDirectionService.deleteByProjectId(record.getId());
            for (Integer did: directionIds){
                ProjectDirectionKey key = new ProjectDirectionKey();
                key.setProjectId(record.getId());
                key.setResearchDirectionId(did);
                projectDirectionService.insert(key);
            }
        }
    }
}
