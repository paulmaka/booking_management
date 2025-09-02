import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    public void shouldReturnOkWithValidToken() {

        String loginPayload = """
                {
                    "email": "testuser@test.com",
                    "password": "password123"
                }
                """;

        Response response = RestAssured.given()     // arrange
                .contentType("application/json")
                .body(loginPayload)
                .when()                             // act
                .post("/auth/login")
                .then()                             // assert
                .statusCode(200)
                .body("token", notNullValue())
                .extract().response();

        System.out.println("Generated token: " + response.jsonPath().getString("token"));

    }

    @Test
    public void shouldReturnUnauthorizedOnInvalidLogin() {
        String loginPayload = """
                {
                    "email": "user@test.com",
                    "password": "badpassword"
                }
                """;

        RestAssured.given()                         // arrange
                .contentType("application/json")
                .body(loginPayload)
                .when()                             // act
                .post("/auth/login")
                .then()                             // assert
                .statusCode(401);

    }

    @Test
    public void shouldReturnOkOnCreateUser() {
        String createUserPayload = """
                {
                  "username": "user3",
                  "email": "user3@example.com",
                  "password": "secret3"
                }
                """;

        Response response = RestAssured.given()     // arrange
                .contentType("application/json")
                .body(createUserPayload)
                .when()                             // act
                .post("/auth/register")
                .then()                             // assert
                .statusCode(200)
                .body("token", notNullValue())
                .extract().response();

        System.out.println("Generated token: " + response.jsonPath().getString("token"));
    }

    @Test
    public void shouldReturnInvalidOnCreateUser() {
        String createUserPayload = """
                {
                  "username": "",
                  "email": "",
                  "password": ""
                }
                """;

        String message =  RestAssured.given()               // arrange
                .contentType("application/json")
                .body(createUserPayload)
                .when()                                     // act
                .post("/auth/register")
                .then()                                     // assert
                .statusCode(400)
                .extract().body().asString();

        System.out.println("Invalid: " + message);
    }

    @Test
    public void shouldReturnEmailExceptionOnCreateUser() {
        String createUserPayload = """
                {
                  "username": "user",
                  "email": "user2@example.com",
                  "password": "secret"
                }
                """;

        String message =  RestAssured.given()               // arrange
                .contentType("application/json")
                .body(createUserPayload)
                .when()                                     // act
                .post("/auth/register")
                .then()                                     // assert
                .statusCode(400)
                .extract().body().asString();

        System.out.println("Email exception: " + message);
    }

}
