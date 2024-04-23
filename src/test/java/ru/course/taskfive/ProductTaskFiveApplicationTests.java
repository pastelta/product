
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
class ProductTaskFiveApplicationTests {
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
	void productServiceTest() {
		given()
				.contentType(ContentType.JSON)
				.when()
				.body("{\n" +
						"  \"instanceId\": null,\n" +
						"  \"productType\": \"Депозит\",\n" +
						"  \"productCode\": \"211\",\n" +
						"  \"registerType\": \"555111\",\n" +
						"  \"mdmCode\": \"100100001\",\n" +
						"  \"contractNumber\": \"3884\",\n" +
						"  \"priority\": 2,\n" +
						"  \"contractDate\": \"2024-02-10\",\n" +
						"  \"interestRatePenalty\": \"1.5\",\n" +
						"  \"minimalBalance\": \"1000\",\n" +
						"  \"thresholdAmount\": \"1000\",\n" +
						"  \"accountingDetails\": \"details\",\n" +
						"  \"rateType\": \"дифференцированная\",\n" +
						"  \"taxPercentageRate\": \"10\",\n" +
						"  \"technicalOverdraftLimitAmount\": \"3000\",\n" +
						"  \"contractId\": 1,\n" +
						"  \"branchCode\": \"777\",\n" +
						"  \"isoCurrencyCode\": \"643\",\n" +
						"  \"urgencyCode\": \"00\",\n" +
						"  \"referenceCode\": 3819,\n" +
						"  \"instanceArrangement\": [\n" +
						"    {\n" +
						"      \"generalAgreementId\": \"154466\",\n" +
						"      \"supplementaryAgreementId\": \"779951\",\n" +
						"      \"arrangementType\": \"НСО\",\n" +
						"      \"shedulerJobId\": 4,\n" +
						"      \"number\": \"116\",\n" +
						"      \"openingDate\": \"2024-04-04\",\n" +
						"      \"closingDate\": \"2025-04-04\",\n" +
						"      \"cancelDate\": \"2025-04-04\",\n" +
						"      \"validityDuration\": 1,\n" +
						"      \"cancellationReason\": \"reason\",\n" +
						"      \"status\": \"открыт\",\n" +
						"      \"interestCalculationDate\": \"2024-04-04\",\n" +
						"      \"interestRate\": \"1.05\",\n" +
						"      \"coefficient\": \"11\",\n" +
						"      \"coefficientAction\": \"1\",\n" +
						"      \"minimumInterestRate\": \"1\",\n" +
						"      \"minimumInterestRateCoefficient\": \"1\",\n" +
						"      \"minimumInterestRateCoefficientAction\": \"повышающий\",\n" +
						"      \"maximalnterestRate\": \"2.5\",\n" +
						"      \"maximalnterestRateCoefficient\": \"5\",\n" +
						"      \"maximalnterestRateCoefficientAction\": \"повышающий\"\n" +
						"    },\n" +
						"    {\n" +
						"      \"generalAgreementId\": \"154466\",\n" +
						"      \"supplementaryAgreementId\": \"779951\",\n" +
						"      \"arrangementType\": \"НСО\",\n" +
						"      \"shedulerJobId\": 4,\n" +
						"      \"number\": \"9999\",\n" +
						"      \"openingDate\": \"2024-04-04\",\n" +
						"      \"closingDate\": \"2025-04-04\",\n" +
						"      \"cancelDate\": \"2025-04-04\",\n" +
						"      \"validityDuration\": 1,\n" +
						"      \"cancellationReason\": \"reason\",\n" +
						"      \"status\": \"открыт\",\n" +
						"      \"interestCalculationDate\": \"2024-04-04\",\n" +
						"      \"interestRate\": \"1.05\",\n" +
						"      \"coefficient\": \"11\",\n" +
						"      \"coefficientAction\": \"1\",\n" +
						"      \"minimumInterestRate\": \"1\",\n" +
						"      \"minimumInterestRateCoefficient\": \"1\",\n" +
						"      \"minimumInterestRateCoefficientAction\": \"повышающий\",\n" +
						"      \"maximalnterestRate\": \"2.5\",\n" +
						"      \"maximalnterestRateCoefficient\": \"5\",\n" +
						"      \"maximalnterestRateCoefficientAction\": \"повышающий\"\n" +
						"    }\n" +
						"  ]\n" +
						"}")
				.post("/api/v1/corporate-settlement-instance/create")
				.then()
				.statusCode(200);

		Assertions.assertEquals(2, a.count()); // agreement
	}
}