package com.rambabusaravanan.feeder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.rambabusaravanan.feeder.model.FeedPosts;
import com.rambabusaravanan.feeder.restclient.PostsService;
import com.rambabusaravanan.feeder.restclient.RestClient;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);

        // get data from calling activity
        Bundle args = getIntent().getExtras();
        Integer postId = args.getInt("id");
        String slug = args.getString("slug");

        PostsService service = RestClient.getInstance().create(PostsService.class);

        // prepare request call
        Call<FeedPosts.Post> call = !TextUtils.isEmpty(slug) ? service.getBySlug(slug) : service.getById(postId);

        // send the request
        call.enqueue(new Callback<FeedPosts.Post>() {
            @Override
            public void onResponse(Call<FeedPosts.Post> call, Response<FeedPosts.Post> response) {
                updateView(response.body());
            }

            @Override
            public void onFailure(Call<FeedPosts.Post> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void updateView(FeedPosts.Post post) {

        // initialize views
        TextView title = (TextView) findViewById(R.id.title);
        TextView author = (TextView) findViewById(R.id.author);
        TextView content = (TextView) findViewById(R.id.content);
        ImageView image = (ImageView) findViewById(R.id.image);

        // set data in views
        title.setText(Html.fromHtml(post.title));
        content.setText(Html.fromHtml(post.content));
        author.setText(post.author.name);
        if (!TextUtils.isEmpty(post.featuredImage)) {
            String imageUrl = post.featuredImage + "?h=180&crop=1&quality=85&strip=all";
            Picasso.with(this).load(imageUrl).placeholder(R.drawable.placeholder).into(image);
        }
    }
}
