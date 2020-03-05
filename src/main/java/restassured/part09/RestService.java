package restassured.part09;

import restassured.part09.users.IUsersService;
import restassured.part09.users.UsersService;

public class RestService {

    private IUsersService usersService;

    public static RestService getRestService() {
        return new RestService();
    }

    private RestService() {
        usersService = new UsersService();
    }

    public IUsersService getUsersService() {
        return usersService;
    }

}

