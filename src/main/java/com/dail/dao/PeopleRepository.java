package com.dail.dao;

import com.dail.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Roger on 2016/12/10.
 */
public interface PeopleRepository extends JpaRepository<People, Long> {

}
