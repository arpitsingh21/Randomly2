package com.example.randomly2.di.module;

import com.example.randomly2.di.scope.ActivityScope;
import com.example.randomly2.ui.FeedAdapter;
import com.example.randomly2.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public FeedAdapter getStarWarsPeopleLIst(FeedAdapter.ClickListener clickListener) {
        return new FeedAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public FeedAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }
}
