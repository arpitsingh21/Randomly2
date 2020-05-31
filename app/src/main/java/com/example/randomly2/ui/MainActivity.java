package com.example.randomly2.ui;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.randomly2.MyApplication;
import com.example.randomly2.R;
import com.example.randomly2.data.PostResponse;
import com.example.randomly2.data.PostResponsePojo;
import com.example.randomly2.di.component.ApplicationComponent;
import com.example.randomly2.di.component.DaggerMainActivityComponent;
import com.example.randomly2.di.component.MainActivityComponent;
import com.example.randomly2.di.module.MainActivityModule;
import com.example.randomly2.di.qualifires.ActivityContext;
import com.example.randomly2.di.qualifires.ApplicationContext;
import com.example.randomly2.retrofit.APIInterface;
import com.example.randomly2.room.MyRoomDatabase;
import com.example.randomly2.room.daos.FeedsDao;
import com.example.randomly2.room.tables.Feed;
import com.example.randomly2.tasks.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FeedAdapter.ClickListener {

    private RecyclerView recyclerView;
    MainActivityComponent mainActivityComponent;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    int currentpage = 0;

    @Inject
    public FeedAdapter recyclerViewAdapter;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        initPagination();

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);
        recyclerView.setAdapter(recyclerViewAdapter);


        checkInternetAndLoadData();




    }

    private void checkInternetAndLoadData() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if (Utils.getInternetStatus()==1){
                    loadData(currentpage);
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext, "No internet, getting data from cache ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    loadDataFromCache(currentpage);
                }
            }
        });
    }


    public void loadData(int page) {

        loadDataFromCache(page);
    }

    public void loadDataFromServer(int page) {
        switch (page) {

            case 0:

                apiInterface.getPosts1().enqueue(new Callback<PostResponsePojo>() {
                    @Override
                    public void onResponse(Call<PostResponsePojo> call, Response<PostResponsePojo> response) {
                        if (response.body() != null) {
                            Log.d("APi response", response.body().toString());
                            cacheFeedinDatabase(response.body().getPosts(), 0);
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponsePojo> call, Throwable t) {
                        Log.d("APi response", t.getMessage().toString());

                    }
                });

                break;
            case 1:
                apiInterface.getPosts2().enqueue(new Callback<PostResponsePojo>() {
                    @Override
                    public void onResponse(Call<PostResponsePojo> call, Response<PostResponsePojo> response) {
                        if (response.body() != null) {
                            Log.d("APi response", response.body().toString());
                            cacheFeedinDatabase(response.body().getPosts(), 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponsePojo> call, Throwable t) {
                        Log.d("APi response", t.getMessage().toString());

                    }
                });
                break;
            case 2:
                apiInterface.getPosts3().enqueue(new Callback<PostResponsePojo>() {
                    @Override
                    public void onResponse(Call<PostResponsePojo> call, Response<PostResponsePojo> response) {
                        if (response.body() != null) {
                            Log.d("APi response", response.body().toString());
                            cacheFeedinDatabase(response.body().getPosts(), 2);
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponsePojo> call, Throwable t) {
                        Log.d("APi response", t.getMessage().toString());
                    }
                });
                break;
        }
    }

    public void loadDataFromCache(final int page) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                MyRoomDatabase db = MyRoomDatabase.getsInstance(getApplicationContext());
                FeedsDao dao = db.getFeedDao();

                final List<Feed> list = dao.getFeedByPage(page);
                if (list.size() > 0) {

                    Log.d("List size", list.size() + "");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerViewAdapter.setData(list);
                        }
                    });
                } else {
                    loadDataFromServer(page);
                    Log.d("List size", null + "");
                }
            }
        });


    }

    public void cacheFeedinDatabase(final List<PostResponse> list, final int page) {


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                MyRoomDatabase db = MyRoomDatabase.getsInstance(getApplicationContext());
                FeedsDao dao = db.getFeedDao();

                long[] ids = dao.bulkInsert(converListToInternal(list, page));

                if (ids.length != list.size()) {
                    Log.d("Inserted ", false + "");
                } else {
                    Log.d("Inserted ", true + "");
                    loadData(page);
                }
            }
        });


    }

    public List<Feed> converListToInternal(List<PostResponse> list, int page) {
        List<Feed> feeds = new ArrayList<>(list.size());
        for (PostResponse postResponse : list) {
            feeds.add(new Feed(page, postResponse.getId(), postResponse.getThumbnailImage(), postResponse.getEventName(), postResponse.getEventDate(), postResponse.getViews(), postResponse.getLikes(), postResponse.getShares()));
        }
        return feeds;
    }

    public void initPagination() {

        final LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;

                            loadData(currentpage+1);
                            Log.d("Current page", currentpage+"");

                        }
                    }
                }
            }
        });
    }


    @Override
    public void onClickFeed(String filmName) {

    }
}
