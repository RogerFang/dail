package com.dail.model;

import java.util.Date;

public class Publication {
    private Integer id;

    private Date createTime;

    private String fullTextUrl;

    private Integer peopleId;

    private String apaText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFullTextUrl() {
        return fullTextUrl;
    }

    public void setFullTextUrl(String fullTextUrl) {
        this.fullTextUrl = fullTextUrl;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public String getApaText() {
        return apaText;
    }

    public void setApaText(String apaText) {
        this.apaText = apaText;
    }
}