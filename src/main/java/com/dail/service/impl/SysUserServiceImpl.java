package com.dail.service.impl;

import com.dail.dao.SysUserRepository;
import com.dail.entity.SysUser;
import com.dail.service.SysUserService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Roger on 2016/12/11.
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Override
    public SysUser findByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }

    @Override
    public SysUser register(SysUser user) {
        entryptPassword(user);
        sysUserRepository.save(user);
        return user;
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
