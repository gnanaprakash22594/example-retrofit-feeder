package com.rambabusaravanan.feeder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.net.Authenticator;
import java.util.List;

/**
 * Created by andro on 6/8/17.
 */

public class FeedPosts {
    public Integer found;
    public List<Post> posts;

    public static class Post {

        @SerializedName("ID")
        public Integer id;

        public String date;

        public String title;

        @SerializedName("featured_image")
        public String featuredImage;

        public String content;

        public Author author;
    }

    public class Author {
        public String name;
    }
}