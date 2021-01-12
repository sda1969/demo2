package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInit {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void init() {
        createPersonTable();
    }

    private void createPersonTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS person (" +
                "id VARCHAR(36) PRIMARY KEY," +
                "name VARCHAR(256) NOT NULL);");
    }
}
