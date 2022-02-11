package com.yalemang.switchskindemo.skin;

import android.app.Application;
import android.view.LayoutInflater;

import com.yalemang.switchskindemo.App;

public class SkinManager {

    private volatile static SkinManager INSTANCE;
    private Application application;

    private SkinManager(){}

    public static SkinManager getInstance(){
        if(INSTANCE == null){
            synchronized (SkinManager.class){
                if(INSTANCE == null){
                    INSTANCE = new SkinManager();
                }
            }
        }

        return INSTANCE;
    }

    public Application getApplication(){
        return application;
    }

    public void initApp(Application app){
        this.application = app;
        //步骤一
        LayoutInflater layoutInflater = LayoutInflater.from(app);
        //步骤二
        app.registerActivityLifecycleCallbacks(new SkinActivityLifecycle());
    }

}
