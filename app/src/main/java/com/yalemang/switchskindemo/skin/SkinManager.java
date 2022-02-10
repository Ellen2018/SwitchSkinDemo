package com.yalemang.switchskindemo.skin;

import android.view.LayoutInflater;

import com.yalemang.switchskindemo.App;

public class SkinManager {

    private volatile static SkinManager INSTANCE;
    private App app;

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

    public void initApp(App app){
        this.app = app;
        //步骤一
        LayoutInflater layoutInflater = LayoutInflater.from(app);
        //步骤二
        app.registerActivityLifecycleCallbacks(new SkinActivityLifecycle());
    }

}
