package com.dail.service.impl;

import com.dail.dao.SysUserMapper;
import com.dail.model.People;
import com.dail.model.SysUser;
import com.dail.service.PeopleService;
import com.dail.service.SysRoleService;
import com.dail.service.SysUserService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private PeopleService peopleService;

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Override
    public SysUser selectById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public SysUser selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int register(SysUser user) {
        entryptPassword(user);
        return userMapper.insertSelective(user);
    }

    /**
     * 获取普通用户
     * @return
     */
    @Override
    public Set<SysUser> selectAllGenerals() {
        return null;
    }

    /**
     * 获取管理员用户
     * @return
     */
    @Override
    public Set<SysUser> selectAllAdmins() {
        return null;
    }


    /**
     * 系统用户与people绑定
     * @return
     */
    @Override
    public int bind(Integer userId, Integer peopleId) {
        return 0;
    }

    @Override
    public int bind(SysUser sysUser, People people) {
        return 0;
    }


    private String entryptPassword(String password, String salt) {
        return new Md5Hash(password, salt+salt, 2).toHex();
    }

    private String generateSalt() {
        return randomNumberGenerator.nextBytes().toHex();
    }

    private void entryptPassword(SysUser user) {
        String salt = generateSalt();
        String hashPassword = entryptPassword(user.getPassword(), salt);
        user.setPassword(hashPassword);
        user.setSalt(salt);
    }
}
