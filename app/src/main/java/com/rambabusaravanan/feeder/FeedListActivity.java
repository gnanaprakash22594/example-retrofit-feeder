package com.rambabusaravanan.feeder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rambabusaravanan.feeder.adapter.FeedAdapter;
import com.rambabusaravanan.feeder.model.FeedPosts;
import com.rambabusaravanan.feeder.restclient.PostsService;
import com.rambabusaravanan.feeder.restclient.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedListActivity extends AppCompatActivity {

    private FeedAdapter feedAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_list);

        // views initialization
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getFeed();
    }

    public void getFeed() {
        PostsService service = RestClient.getInstance().create(PostsService.class);
        service.get().enqueue(new Callback<FeedPosts>() {
            @Override
            public void onResponse(Call<FeedPosts> call, Response<FeedPosts> response) {

                // set post list to recycler view
                feedAdapter = new FeedAdapter(response.body().posts);
                recyclerView.setAdapter(feedAdapter);
            }

            @Override
            public void onFailure(Call<FeedPosts> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
