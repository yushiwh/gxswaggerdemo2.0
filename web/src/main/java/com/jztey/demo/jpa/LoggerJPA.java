package com.jztey.demo.jpa;

import com.jztey.demo.domain.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 操作JPA的LOGGER的日志
 */
public interface LoggerJPA
        extends JpaRepository<LoggerEntity, Long> {

}

