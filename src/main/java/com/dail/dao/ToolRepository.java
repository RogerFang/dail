package com.dail.dao;

import com.dail.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Roger on 2016/12/11.
 */
public interface ToolRepository extends JpaRepository<Tool, Long> {
}
