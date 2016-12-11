package com.dail.service;

import com.dail.model.SysConst;

/**
 * Created by Roger on 2016/12/12.
 */
public interface SysConstService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysConst record);

    SysConst selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysConst record);
}
