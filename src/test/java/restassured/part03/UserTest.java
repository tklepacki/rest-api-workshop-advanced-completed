package restassured.part03;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

public class UserTest {

    @Test
    public void getUserTest() {
        when().
                get("https://reqres.in/api/users/2").
                then().body(matchesJsonSchemaInClasspath("user.json")).
                statusCode(200).
                time(lessThan(2L), TimeUnit.SECONDS).
                log().all();
    }

    @Test
    public void getUserListTest() {
        when().
                get("https://reqres.in/api/users?page=2").
                then().body(matchesJsonSchemaInClasspath("userList.json")).
                statusCode(200).
                time(lessThan(2L), TimeUnit.SECONDS).
                log().all();
    }

}