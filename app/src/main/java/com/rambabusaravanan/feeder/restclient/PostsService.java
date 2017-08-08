package com.rambabusaravanan.feeder.restclient;

import com.rambabusaravanan.feeder.model.FeedPosts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by andro on 6/8/17.
 */

public interface PostsService {

    @GET("/rest/v1.1/sites/techcrunch.com/posts?fields=ID,title,content,date,featured_image")
    Call<FeedPosts> get();

    @GET("/rest/v1.1/sites/techcrunch.com/posts/{postId}")
    Call<FeedPosts.Post> getById(@Path("postId") Integer id);

    @GET("/rest/v1.1/sites/techcrunch.com/posts/slug:{slug}")
    Call<FeedPosts.Post> getBySlug(@Path("slug") String slug);
}
