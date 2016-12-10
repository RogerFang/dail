package com.dail.service.impl;

import com.dail.dao.PeopleRepository;
import com.dail.entity.People;
import com.dail.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Roger on 2016/12/10.
 */
@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public People findById(Long id) {
        return peopleRepository.findOne(id);
    }
}
