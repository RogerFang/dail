package com.dail.model;

import java.util.Date;
import java.util.Set;

public class SysUser {
    private Integer id;

    private String password;

    private String username;

    private Integer peopleId;

    private Date createTime;

    private Boolean enabled;

    private String salt;

    private Set<SysRole> roleSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<SysRole> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<SysRole> roleSet) {
        this.roleSet = roleSet;
    }

    public String getCredentialSalt(){
        return this.salt + this.salt;
    }
}