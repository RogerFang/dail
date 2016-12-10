package com.dail.dao;

import com.dail.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Roger on 2016/12/11.
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByUsername(String username);
}
