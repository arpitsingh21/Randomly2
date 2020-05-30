package com.example.randomly2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostResponsePojo {

    @SerializedName("posts")
    @Expose
    private List<PostResponse> posts = null;
    @SerializedName("page")
    @Expose
    private Integer page;

    public List<PostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponse> posts) {
        this.posts = posts;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "PostResponsePojo{" +
                "posts=" + posts +
                ", page=" + page +
                '}';
    }
}
