package com.yalemang.switchskindemo;

import android.app.Application;

import com.yalemang.switchskindemo.skin.SkinManager;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().initApp(this);
    }
}
