
package ru.course.taskfive;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.course.taskfive.repository.AgreementRepositoryable;
import ru.course.taskfive.repository.TppRegisterRepositoryable;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegisterTaskFiveApplicationTests {
	@Autowired
	private TppRegisterRepositoryable r;
	@Autowired
	private AgreementRepositoryable a;
	@LocalServerPort
	private Integer port;

	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
		r.deleteAll();
	}
	@Test
	@Sql({"/schema.sql"})
	void registerServiceTest() {
		given()
				.contentType(ContentType.JSON)
				.when()
				.body("{\n" +
						"    \"instanceId\": 2,\n" +
						"    \"registryTypeCode\": \"555111\",\n" +
						"    \"currencyCode\":\"643\",\n" +
						"    \"branchCode\":\"777\",\n" +
						"    \"priorityCode\":\"00\",\n" +
						"    \"mdmCode\":\"245020\"\n" +
						"}")
				.post("/api/v1/corporate-settlement-account/create")
				.then()
				.statusCode(200);

		Assertions.assertEquals(1, r.count()); // agreement
	}
}