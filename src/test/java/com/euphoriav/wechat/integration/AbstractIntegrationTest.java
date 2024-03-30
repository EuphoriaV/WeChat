package com.euphoriav.wechat.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

@Rollback
@Transactional
@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

    static PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:15");

    static {
        CONTAINER.start();
    }

    @DynamicPropertySource
    private static void configureDatabaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", CONTAINER::getUsername);
        registry.add("spring.datasource.password", CONTAINER::getPassword);
    }
}
