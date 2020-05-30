package com.example.randomly2.di.component;

import android.content.Context;


import com.example.randomly2.di.qualifires.ActivityContext;
import com.example.randomly2.di.scope.ActivityScope;
import com.example.randomly2.ui.MainActivity;

import dagger.Component;


@ActivityScope
@Component(modules = AdapterModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();

    void injectMainActivity(MainActivity mainActivity);
}
