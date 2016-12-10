package com.dail.dao;

import com.dail.entity.Logo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Roger on 2016/12/10.
 */
public interface LogoRepository extends JpaRepository<Logo, Long> {
}
