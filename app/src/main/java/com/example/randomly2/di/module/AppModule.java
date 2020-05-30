package com.example.randomly2.di.module;

import android.content.Context;

import com.example.randomly2.di.qualifires.ApplicationContext;
import com.example.randomly2.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
