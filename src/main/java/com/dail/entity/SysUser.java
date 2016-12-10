package com.dail.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Roger on 2016/12/10.
 */
@Entity
public class SysUser {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String salt;
    private boolean enabled;
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Set<SysRole> roles;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    private People people;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取密码盐
     * @return
     */
    public String getCredentialSalt(){
        return this.salt + this.salt;
    }

    public Set<String> getRoleStrs(){
        Set<String> set = new HashSet<>();
        for (SysRole role: this.getRoles()){
            set.add(role.getName());
        }
        return set;
    }
}
