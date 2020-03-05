package restassured.part02;

import org.junit.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserTest {

    @Test
    public void getUserTest() {
        when().
                get("https://reqres.in/api/users/2").
                then().body(matchesJsonSchemaInClasspath("user.json")).
                statusCode(200).
                log().all();
    }

    @Test
    public void getUserListTest() {
        when().
                get("https://reqres.in/api/users?page=2").
                then().body(matchesJsonSchemaInClasspath("userList.json")).
                statusCode(200).
                log().all();
    }

}