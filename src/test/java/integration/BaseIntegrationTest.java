package integration;

import com.example.Profile.ProfileApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.CockroachContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ProfileApplication.class, RestTemplate.class})
@ActiveProfiles("mock-integration")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseIntegrationTest {
    private static final String BASE_URL = "http://localhost:%s";

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;
    @LocalServerPort
    int port;

    @Autowired
    private Environment environment;

    public static CockroachContainer cockroachContainer;

    protected String getBaseUrl() {
        log.info(String.format("passed port is: %s, environment one is: %s", port, environment.getProperty("local.server.port")));
        return String.format(BASE_URL, port);
    }

    private static final String COCKROACH_DOCKER_IMAGE = "cockroachdb/cockroach:v22.2.9";

    static {
        cockroachContainer = new CockroachContainer(COCKROACH_DOCKER_IMAGE)
                .withReuse(true);
        Startables.deepStart(List.of(cockroachContainer)).join();
    }

    @DynamicPropertySource
    static void testProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", cockroachContainer::getJdbcUrl);
        registry.add("spring.datasource.username", cockroachContainer::getUsername);
        registry.add("spring.datasource.password", cockroachContainer::getPassword);
    }

    @BeforeEach
    void init() {
        configurableApplicationContext.start();
    }

    @Test
    void assertTestcontainersAreRunning() {
        assertThat(cockroachContainer.isRunning()).isTrue();
    }
}
