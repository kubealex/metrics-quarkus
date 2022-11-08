package org.acme;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
@QuarkusIntegrationTest
public class MetricsResourceIT extends MetricsResourceTest {

    @Test
    public void testRunEndpoint() {
        given()
          .when().get("/run")
          .then()
             .statusCode(200)
             .body(is("OK"));
    }
    // Execute the same tests but in native mode.
}
