package com.claims.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.claims.demo.testsupport.TestContainersSetup;

@SpringBootTest
@ActiveProfiles("test")
public class MyRepositoryTest {

    static {
        TestContainersSetup.init();
    }

    @Test
    void testJdbcTemplate() {
        // Inject JdbcTemplate and test queries
    }
}