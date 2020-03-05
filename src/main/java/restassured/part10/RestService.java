package restassured.part10;

import restassured.part10.users.IUsersService;
import restassured.part10.users.UsersService;
import restassured.part10.posts.IPostsService;
import restassured.part10.posts.PostsService;

public class RestService {

    private IUsersService usersService;
    private IPostsService postsService;

    public static RestService getRestService() {
        return new RestService();
    }

    private RestService() {
        usersService = new UsersService();
        postsService = new PostsService();
    }

    public IUsersService getUsersService() {
        return usersService;
    }

    public IPostsService getPostsService() {
        return postsService;
    }
}


