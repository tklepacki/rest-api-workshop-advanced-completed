package restassured.part11;

import org.junit.Test;
import restassured.part11.posts.Post;

import static org.hamcrest.Matchers.*;

public class PostTest {

    private final String addPostBody = FileHelper.generateStringFromResource("addPostBody.json");
    private final String editPostBody = FileHelper.generateStringFromResource("editPostBody.json");

    @Test
    public void addPostTest() {

        Post post = new Post();
        post.setTitle("TestTitle");
        post.setAuthor("TestAuthor");

        Integer addedPostId = RestService.getRestService().getPostsService().addPost(post).
                then().
                body("author", equalTo(post.getAuthor())).
                body("title", equalTo(post.getTitle())).
                statusCode(201).
                extract().
                path("id");

        RestService.getRestService().getPostsService().getPost(addedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo(post.getAuthor())).
                body("title", equalTo(post.getTitle())).
                statusCode(200);
    }

    @Test
    public void editPostTest() {

        Post post = new Post();
        post.setTitle("TestTitle");
        post.setAuthor("TestAuthor");

        Integer addedPostId = RestService.getRestService().getPostsService().addPost(post).
                then().
                statusCode(201).
                extract().
                path("id");

        Post updatedPost = new Post();
        post.setTitle("TestTitleUpdated");
        post.setAuthor("TestAuthorUpdated");

        Integer updatedPostId = RestService.getRestService().getPostsService().editPost(addedPostId, updatedPost).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo(updatedPost.getAuthor())).
                body("title", equalTo(updatedPost.getTitle())).
                statusCode(200).
                extract().
                path("id");

        RestService.getRestService().getPostsService().getPost(updatedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo(updatedPost.getAuthor())).
                body("title", equalTo(updatedPost.getTitle())).
                statusCode(200);
    }

    @Test
    public void getPostListTest() {

        Post post = new Post();
        post.setTitle("TestTitle");
        post.setAuthor("TestAuthor");

        Integer addedPostId = RestService.getRestService().getPostsService().addPost(post).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getRestService().getPostsService().getPostList().
                then().
                body("find { it.id == " + addedPostId + " }.id", equalTo(addedPostId)).
                body("find { it.id == " + addedPostId + " }.title", equalTo(post.getTitle())).
                body("find { it.id == " + addedPostId + " }.author", equalTo(post.getAuthor())).
                statusCode(200);
    }

    @Test
    public void deletePostTest() {

        Post post = new Post();
        post.setTitle("TestTitle");
        post.setAuthor("TestAuthor");

        Integer addedPostId = RestService.getRestService().getPostsService().addPost(post).
                then().
                statusCode(201).
                extract().
                path("id");

        RestService.getRestService().getPostsService().deletePost(addedPostId).
                then().
                statusCode(200);

        RestService.getRestService().getPostsService().getPostList().
                then().
                body("id", not(hasItems(addedPostId)));
    }

    @Test
    public void addPostBodyFromFileTest() {
        Integer addedPostId = RestService.getRestService().getPostsService().addPost(addPostBody).
                then().
                body("author", equalTo("TestAuthor")).
                body("title", equalTo("TestTitle")).
                statusCode(201).
                extract().
                path("id");

        RestService.getRestService().getPostsService().getPost(addedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo("TestAuthor")).
                body("title", equalTo("TestTitle")).
                statusCode(200);
    }

    @Test
    public void editPostBodyFromFileTest() {

        Integer addedPostId = RestService.getRestService().getPostsService().addPost(addPostBody).
                then().
                statusCode(201).
                extract().
                path("id");

        Integer updatedPostId = RestService.getRestService().getPostsService().editPost(addedPostId, editPostBody).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo("TestAuthorUpdated")).
                body("title", equalTo("TestTitleUpdated")).
                statusCode(200).
                extract().
                path("id");

        RestService.getRestService().getPostsService().getPost(updatedPostId).
                then().
                body("id", equalTo(addedPostId)).
                body("author", equalTo("TestAuthorUpdated")).
                body("title", equalTo("TestTitleUpdated")).
                statusCode(200);
    }

}