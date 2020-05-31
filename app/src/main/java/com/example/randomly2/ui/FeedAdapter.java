package com.example.randomly2.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.randomly2.R;
import com.example.randomly2.data.PostResponse;
import com.example.randomly2.room.tables.Feed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private List<Feed> data;
    private FeedAdapter.ClickListener clickListener;

    @Inject
    public FeedAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_feed_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getEvent_name());
        holder.likes.setText(String.valueOf(data.get(position).getLikes()));
        holder.shares.setText(String.valueOf(data.get(position).getShares()));
        holder.views.setText(String.valueOf(data.get(position).getViews()));

        Picasso.with(holder.image.getContext())
                .load(data.get(position).getThumbnail_image())
                .resize(200,200)
                .onlyScaleDown()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView likes;
        private TextView views;
        private TextView shares;
        private ImageView image;


        ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            likes = itemView.findViewById(R.id.likes);
            views = itemView.findViewById(R.id.views);
            shares = itemView.findViewById(R.id.shares);
            image = itemView.findViewById(R.id.image);
        }
    }

    public interface ClickListener {
        void onClickFeed(String filmName);
    }

    public void setData(List<Feed> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}