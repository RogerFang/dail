package com.dail.service;


import com.dail.model.SysRole;

import java.util.List;
import java.util.Set;

/**
 * Created by Roger on 2016/12/11.
 */
public interface SysRoleService {

    List<SysRole> selectAll();

    SysRole selectByName(String name);

    Set<SysRole> selectByUserId(Integer userId);

}
