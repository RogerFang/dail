package com.dail.entity;

import javax.persistence.*;
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
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Set<SysRole> roles;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    private People people;

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
}
