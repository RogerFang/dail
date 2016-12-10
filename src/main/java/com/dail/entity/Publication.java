package com.dail.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roger on 2016/12/10.
 */
@Entity
public class Publication {

    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String apaText;
    @Column(length = 500)
    private String fullTextUrl;
    @Column(columnDefinition = "DATETIME")
    private Date createTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    private People people;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApaText() {
        return apaText;
    }

    public void setApaText(String apaText) {
        this.apaText = apaText;
    }

    public String getFullTextUrl() {
        return fullTextUrl;
    }

    public void setFullTextUrl(String fullTextUrl) {
        this.fullTextUrl = fullTextUrl;
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
}
