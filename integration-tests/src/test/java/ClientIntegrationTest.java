import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class ClientIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    public void shouldReturnClientsWithValidToken() {
        String loginPayload = """
                {
                    "email": "testuser@test.com",
                    "password": "password123"
                }
                """;

        String token = RestAssured.given()          // arrange
                .contentType("application/json")
                .body(loginPayload)
                .when()                             // act
                .post("/auth/login")
                .then()                             // assert
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("token");


        Response response = RestAssured.given()     // arrange
                .header("Authorization", "Bearer " + token)
                .when()                             // act
                .get("/api/clients")
                .then()                             // assert
                .statusCode(200)
                .body("clients",  notNullValue())
                .extract().response();

        System.out.println("GET clients request: " + response.getBody().asString());

    }
}
