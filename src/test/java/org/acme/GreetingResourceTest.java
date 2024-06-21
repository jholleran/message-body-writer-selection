package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.Header;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Greeting response: Hello from Quarkus REST"));
    }

    @Test
    void testWithNoAcceptHeader() {
        // Prevent RestAssured from setting any Accept header
        final var header = new Header("Accept", null);

        given()
                .when()
                .header(header)
                .get("/hello")
                .then()
                .statusCode(200)
                .body(is("Greeting response: Hello from Quarkus REST"));
    }
}