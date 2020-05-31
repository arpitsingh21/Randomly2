package com.example.randomly2.room.tables;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "feed")
public class Feed {

    @ColumnInfo(name = "id")
    @NonNull
    private String id;

    @ColumnInfo(name = "page_id")
    @NonNull
    private int page_id;

    @ColumnInfo(name = "thumbnail_image")
    @NonNull
    private String thumbnail_image;

    @PrimaryKey
    @ColumnInfo(name = "event_name")
    @NonNull
    private String event_name;

    @ColumnInfo(name = "event_date")
    @NonNull
    private Integer event_date;

    @ColumnInfo(name = "views")
    @NonNull
    private int views;
    @ColumnInfo(name = "likes")
    @NonNull
    private int likes;

    @ColumnInfo(name = "shares")
    @NonNull
    private int shares;


    public Feed(@NonNull int page_id, @NonNull String id, @NonNull String thumbnail_image, @NonNull String event_name, @NonNull Integer event_date, int views, int likes, int shares) {
        this.page_id = page_id;
        this.id = id;
        this.thumbnail_image = thumbnail_image;
        this.event_name = event_name;
        this.event_date = event_date;
        this.views = views;
        this.likes = likes;
        this.shares = shares;
    }

    @NonNull
    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(@NonNull int page_id) {
        this.page_id = page_id;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getThumbnail_image() {
        return thumbnail_image;
    }

    public void setThumbnail_image(@NonNull String thumbnail_image) {
        this.thumbnail_image = thumbnail_image;
    }

    @NonNull
    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(@NonNull String event_name) {
        this.event_name = event_name;
    }

    @NonNull
    public Integer getEvent_date() {
        return event_date;
    }

    public void setEvent_date(@NonNull Integer event_date) {
        this.event_date = event_date;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }
}
