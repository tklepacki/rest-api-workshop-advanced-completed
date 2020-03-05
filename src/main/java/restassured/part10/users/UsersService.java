package restassured.part10.users;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UsersService implements IUsersService {

    private static final String USERS_ENDPOINT = "https://reqres.in/api/users";

    RequestSpecification requestSpec = new RequestSpecBuilder().
            setBaseUri(USERS_ENDPOINT).
            build();

    public UsersService() {
        super();
    }

    public Response getUser(Integer userId) {
        Response response = given().
                spec(requestSpec).
                pathParam("userId", userId).
                when().
                get("{userId}");
        return response;
    }

    @Override
    public Response getUserList(Integer pageId) {
        Response response = given().
                spec(requestSpec).
                queryParam("page", pageId).
                when().
                get();
        return response;
    }

}