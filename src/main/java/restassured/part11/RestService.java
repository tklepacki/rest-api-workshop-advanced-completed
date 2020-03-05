package restassured.part11;

import restassured.part11.users.IUsersService;
import restassured.part11.users.UsersService;
import restassured.part11.posts.IPostsService;
import restassured.part11.posts.PostsService;

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


