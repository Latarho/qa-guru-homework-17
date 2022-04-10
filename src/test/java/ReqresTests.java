import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import pojo.User;

import static common.EndPoints.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.is;

public class ReqresTests {
    Gson gson = new Gson();
    User user = new User("Serg", "QA Engineer");
    String jsonUser = gson.toJson(user);


    @Test
    public void getListUsersTest() {
        given()
                .when()
                .get(BASE_URI + LIST_USERS)
                .then()
                .statusCode(SC_OK)
                .body("total", is(12));
    }

    @Test
    public void getSingleUserTest() {
        given()
                .when()
                .get(BASE_URI + SINGLE_USER)
                .then()
                .statusCode(SC_OK)
                .body("data.id", is(2));
    }

    @Test
    public void getSingleUserNotFoundTest() {
        given()
                .when()
                .get(BASE_URI + SINGLE_USER_NOT_FOUND)
                .then()
                .statusCode(SC_NOT_FOUND);
    }

    @Test
    public void createUserTest() {
        given()
                .contentType(JSON)
                .body(jsonUser)
                .when()
                .post(BASE_URI + CREATE_USER)
                .then()
                .statusCode(SC_CREATED)
                .body("name", is("Serg"));
    }

    @Test
    public void updateUserTest() {
        given()
                .contentType(JSON)
                .body(jsonUser)
                .when()
                .put(BASE_URI + UPDATE_USER)
                .then()
                .statusCode(SC_OK)
                .body("name", is("Serg"));
    }
}