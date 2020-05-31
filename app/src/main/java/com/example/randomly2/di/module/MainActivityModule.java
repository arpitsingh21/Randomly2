package com.example.randomly2.di.module;

import android.content.Context;

import com.example.randomly2.di.qualifires.ActivityContext;
import com.example.randomly2.di.scope.ActivityScope;
import com.example.randomly2.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private MainActivity mainActivity;

    public Context context;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
