package com.example.randomly2;

import android.app.Activity;
import android.app.Application;

import com.example.randomly2.di.component.ApplicationComponent;
import com.example.randomly2.di.component.DaggerApplicationComponent;
import com.example.randomly2.di.module.AppModule;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override

    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().appModule(new AppModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}