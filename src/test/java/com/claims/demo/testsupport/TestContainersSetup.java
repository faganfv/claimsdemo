package com.claims.demo.testsupport;

import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainersSetup {

    public static final PostgreSQLContainer<?> POSTGRES;

    static {
        POSTGRES = new PostgreSQLContainer<>("postgres:15-alpine")
                .withDatabaseName("testdb")
                .withUsername("test")
                .withPassword("test");
        POSTGRES.start();

        System.setProperty("spring.datasource.url", POSTGRES.getJdbcUrl());
        System.setProperty("spring.datasource.username", POSTGRES.getUsername());
        System.setProperty("spring.datasource.password", POSTGRES.getPassword());
        System.setProperty("spring.liquibase.change-log", "classpath:db/changelog/db.changelog.yml");
    }

    public static void init() {

    }

}
