package restassured.part08;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

@RunWith(DataProviderRunner.class)
public class UserTest {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api/users").
                build();
    }

    @DataProvider
    public static Object[][] userData() {
        return new Object[][]{
                {1, "george.bluth@reqres.in", "George", "Bluth", "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"},
                {2, "janet.weaver@reqres.in", "Janet", "Weaver", "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"},
                {3, "emma.wong@reqres.in", "Emma", "Wong", "https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg"}
        };
    }

    @DataProvider
    public static Object[][] userListData() {
        return new Object[][]{
                {1, 6, 12, 2, 1, "george.bluth@reqres.in", "George", "Bluth", "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"},
                {2, 6, 12, 2, 7, "michael.lawson@reqres.in", "Michael", "Lawson", "https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg"},
        };
    }

    @Test
    @UseDataProvider("userData")
    public void getUserTest(Integer userId, String email, String firstName, String lastName, String avatar) {
        given().
                spec(requestSpec).
                pathParam("userId", userId).
                when().
                get("/{userId}").
                then().
                body(matchesJsonSchemaInClasspath("user.json")).
                rootPath("data").
                body("id", equalTo(userId)).
                body("email", equalTo(email)).
                body("first_name", equalTo(firstName)).
                body("last_name", equalTo(lastName)).
                body("avatar", equalTo(avatar)).
                contentType("application/json;charset=UTF-8").
                statusCode(200);
    }

    @Test
    @UseDataProvider("userListData")
    public void getUserListTest(Integer pageId, Integer perPage, Integer total, Integer totalPages, Integer userId, String email, String firstName, String lastName, String avatar) {
        given().
                spec(requestSpec).
                queryParam("page", pageId).
                when().
                get().
                then().
                body(matchesJsonSchemaInClasspath("userList.json")).
                body("page", equalTo(pageId)).
                body("per_page", equalTo(perPage)).
                body("total", equalTo(total)).
                body("total_pages", equalTo(totalPages)).

                rootPath("data").
                body("id[0]", equalTo(userId)).
                body("email[0]", equalTo(email)).
                body("first_name[0]", equalTo(firstName)).
                body("last_name[0]", equalTo(lastName)).
                body("avatar[0]", equalTo(avatar)).

                contentType("application/json;charset=UTF-8").
                statusCode(200);
    }

}