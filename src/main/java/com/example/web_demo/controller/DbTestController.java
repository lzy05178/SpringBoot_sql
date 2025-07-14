package com.example.web_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class DbTestController {
    @Autowired
    private JdbcTemplate jdbc;

    @GetMapping("/dbtest")
    public Map<String, Object> dbtest() {
        Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM accounts", Integer.class);
        return Collections.singletonMap("rowCount", count);
    }
}
