package com.dail.dao;

import com.dail.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Roger on 2016/12/10.
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
