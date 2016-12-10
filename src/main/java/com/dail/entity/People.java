package com.dail.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Roger on 2016/12/10.
 */
@Entity
public class People {

    @Id
    @GeneratedValue
    private Long id;
    // 人名
    private String name;
    // 头像url
    private String imgUrl;
    // 描述
    @Column(columnDefinition = "TEXT")
    private String description;
    // 职位
    private String position;
    // 顺序
    private Integer sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY, mappedBy = "people")
    private Set<Publication> publications;
    @ManyToMany(fetch=FetchType.LAZY)
    private Set<ResearchDirection> researchDirections;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Set<Publication> getPublications() {
        return publications;
    }

    public void setPublications(Set<Publication> publications) {
        this.publications = publications;
    }

    public Set<ResearchDirection> getResearchDirections() {
        return researchDirections;
    }

    public void setResearchDirections(Set<ResearchDirection> researchDirections) {
        this.researchDirections = researchDirections;
    }
}
