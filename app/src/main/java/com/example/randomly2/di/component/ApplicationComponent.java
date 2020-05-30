package com.example.randomly2.di.component;

import android.content.Context;

import com.example.randomly2.MyApplication;
import com.example.randomly2.di.module.AppModule;
import com.example.randomly2.di.module.RetrofitModule;
import com.example.randomly2.di.qualifires.ApplicationContext;
import com.example.randomly2.di.scope.ApplicationScope;
import com.example.randomly2.retrofit.APIInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public APIInterface getApiInterface();

    @ApplicationContext
    public Context getContext();

    public void injectApplication(MyApplication myApplication);
}
