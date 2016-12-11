package com.dail.service.impl;

import com.dail.dao.SysConstMapper;
import com.dail.model.SysConst;
import com.dail.service.SysConstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Roger on 2016/12/12.
 */
@Service
public class SysConstServiceImpl implements SysConstService {

    @Autowired
    private SysConstMapper sysConstMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysConstMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(SysConst record) {
        return sysConstMapper.insertSelective(record);
    }

    @Override
    public SysConst selectByPrimaryKey(Integer id) {
        return sysConstMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysConst record) {
        return sysConstMapper.updateByPrimaryKeySelective(record);
    }
}
