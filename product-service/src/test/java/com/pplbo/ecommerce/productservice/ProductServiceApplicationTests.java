package com.pplbo.ecommerce.productservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static{
		postgreSQLContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
					{
				     "name": "Baseus Encok WM01",
				     "price": 199000,
				     "description": "TWS terbaik sealam dunia tahan 14 tahun pemakaian.",
				     "brandId": 1,
				     "image": "http://www.example.com/baseusencokwm01.jpg"
				 	}
				
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", notNullValue())
				.body("name", equalTo("Baseus Encok WM01"))
				.body("description", equalTo("TWS terbaik sealam dunia tahan 14 tahun pemakaian."))
				.body("price", equalTo(199000));
	}

}
