package com.yalemang.switchskindemo.skin;

import android.app.Application;
import android.util.Log;
import android.view.LayoutInflater;

import com.yalemang.switchskindemo.App;

import java.util.ArrayList;
import java.util.List;

public class SkinManager {

    private volatile static SkinManager INSTANCE;
    private Application application;
    private List<String> skinNames = new ArrayList<>();
    private String currentSkin = "skin_default.apk";
    private static final String DEFAULT_SKIN_NAME = "skin_default.apk";
    private SkinActivityLifecycle skinActivityLifecycle;

    private SkinManager(){
        //初始化皮肤数据，当然这里可以网络下载即可，但是为了方便
        //笔者就用assets目录copy到本地目录的方式模拟网络加载皮肤过程
        skinNames.add("skin_blue.apk");
        skinNames.add("skin_red.apk");
        skinNames.add("skin_black.apk");
        skinNames.add("skin_green.apk");
        skinNames.add("skin_default.apk");
    }

    public List<String> getSkinData(){
        return skinNames;
    }

    /**
     * 切换皮肤
     * @param skinName
     */
    public void switchSkin(String skinName){
        this.currentSkin = skinName;
        skinActivityLifecycle.switchSkin();
    }

    public boolean isDefaultSkin(){
        return currentSkin.equals(DEFAULT_SKIN_NAME);
    }

    public String getCurrentSkin(){
        return currentSkin;
    }

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
        app.registerActivityLifecycleCallbacks(skinActivityLifecycle = new SkinActivityLifecycle());
    }

}
