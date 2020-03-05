package restassured.part11.posts;

import io.restassured.response.Response;

public interface IPostsService {

    Response getPost(Integer postId);

    Response getPostList();

    Response addPost(Post post);

    Response addPost(String postBody);

    Response editPost(Integer postId, Post post);

    Response editPost(Integer postId, String postBody);

    Response deletePost(Integer postId);

}