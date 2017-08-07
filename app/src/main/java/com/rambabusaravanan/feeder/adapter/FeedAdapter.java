package com.rambabusaravanan.feeder.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rambabusaravanan.feeder.FeedDetail;
import com.rambabusaravanan.feeder.R;
import com.rambabusaravanan.feeder.model.FeedPosts;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by andro on 6/8/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private List<FeedPosts.Post> posts;

    public FeedAdapter(List<FeedPosts.Post> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // create a view for list item
        View view = inflater.inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (this.posts != null) {

            // update one post item view with data
            final FeedPosts.Post post = posts.get(position);
            holder.title.setText(Html.fromHtml(post.title));
            if (!TextUtils.isEmpty(post.featuredImage)) {
                String imageUrl = post.featuredImage + "?h=180&crop=1&quality=85&strip=all";
                Picasso.with(holder.image.getContext()).load(imageUrl).placeholder(R.drawable.placeholder).into(holder.image);
            }

            // set click listener
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(), FeedDetail.class);
                    intent.putExtra("id", post.id);     // pass data to next activity
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.posts != null ? posts.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
        }
    }
}
