package com.dail.service.impl;

import com.dail.constant.RoleEnum;
import com.dail.dao.SysUserMapper;
import com.dail.model.People;
import com.dail.model.SysRole;
import com.dail.model.SysUser;
import com.dail.model.SysUserRoleKey;
import com.dail.service.PeopleService;
import com.dail.service.SysRoleService;
import com.dail.service.SysUserRoleService;
import com.dail.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private PeopleService peopleService;

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysUser selectById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public SysUser selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Transactional
    @Override
    public void register(SysUser user) {
        entryptPassword(user);
        user.setEnabled(true);
        user.setCreateTime(new Date());
        userMapper.insertSelective(user);
        // default role: general
        SysRole role = sysRoleService.selectByName(RoleEnum.GENERAL.name());
        SysUserRoleKey key = new SysUserRoleKey(user.getId(), role.getId());
        sysUserRoleService.insert(key);
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
        SysUser user = new SysUser();
        user.setId(userId);
        user.setPeopleId(peopleId);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int bind(SysUser sysUser, People people) {
        return 0;
    }

    @Override
    public SysUser selectByIdWithPeople(Integer id) {
        return userMapper.selectByIdWithPeople(id);
    }

    @Override
    public SysUser selectByIdWithPeopleInfo(Integer id) {
        return userMapper.selectByIdWithPeopleInfo(id);
    }

    @Override
    public PageInfo<SysUser> page(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<SysUser> users = userMapper.selectAllWithPeople();
        return new PageInfo<>(users);
    }

    @Override
    public void resetPassword(SysUser sysUser) {
        entryptPassword(sysUser);
        userMapper.updateByPrimaryKeySelective(sysUser);
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
