package com.dail.dao;

import com.dail.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Roger on 2016/12/11.
 */
public interface NewsRepository extends JpaRepository<News, Long> {
}
