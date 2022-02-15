package com.yalemang.switchskindemo.skin;

import android.app.Application;
import android.util.Log;
import android.view.LayoutInflater;

import com.yalemang.switchskindemo.App;

import java.util.ArrayList;
import java.util.List;

//皮肤管理类
public class SkinManager {

    //单例对象
    private volatile static SkinManager INSTANCE;
    //Application对象
    private Application application;
    //皮肤名字集合
    private List<String> skinNames = new ArrayList<>();
    //记录当前应用的皮肤名
    private String currentSkin = "skin_default.apk";
    //记录默认的皮肤名
    private static final String DEFAULT_SKIN_NAME = "skin_default.apk";
    //应用Activity生命周期监听
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

    /**
     * 是否是默认皮肤
     * @return
     */
    public boolean isDefaultSkin(){
        return currentSkin.equals(DEFAULT_SKIN_NAME);
    }

    /**
     * 获取到当前的皮肤名
     * @return
     */
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

    /**
     * 皮肤管理初始化
     * @param app
     */
    public void initApp(Application app){
        this.application = app;
        //对所有Activity的声明周期进行监听
        app.registerActivityLifecycleCallbacks(skinActivityLifecycle = new SkinActivityLifecycle());
    }

}
