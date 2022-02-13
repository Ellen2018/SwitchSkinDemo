package com.yalemang.switchskindemo.skin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.LayoutInflaterCompat;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SkinActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private List<Activity> activeActivityList = new ArrayList<>();

    @Override
    @SuppressLint("SoonBlockedPrivateApi")
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        activeActivityList.add(activity);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        //反射mFactorySet,Android Q及以上已经失效-> 报not field 异常
        //Android Q以上setFactory2问题
        //http://www.javashuo.com/article/p-sheppkca-ds.html
        forceSetFactory2(layoutInflater);
    }

    /**
     * 最新的方式，适配Android Q
     * @param inflater
     */
    private static void forceSetFactory2(LayoutInflater inflater) {
        Class<LayoutInflaterCompat> compatClass = LayoutInflaterCompat.class;
        Class<LayoutInflater> inflaterClass = LayoutInflater.class;
        try {
            Field sCheckedField = compatClass.getDeclaredField("sCheckedField");
            sCheckedField.setAccessible(true);
            sCheckedField.setBoolean(inflater, false);
            Field mFactory = inflaterClass.getDeclaredField("mFactory");
            mFactory.setAccessible(true);
            Field mFactory2 = inflaterClass.getDeclaredField("mFactory2");
            mFactory2.setAccessible(true);
            //自定义的Factory2
            SkinLayoutFactory skinLayoutFactory = new SkinLayoutFactory();
            mFactory2.set(inflater, skinLayoutFactory);
            mFactory.set(inflater, skinLayoutFactory);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
       activeActivityList.remove(activity);
    }

    public void switchSkin(){
        for(Activity activity:activeActivityList){
            //重新使用资源
            activity.recreate();
            Log.d("Ellen2018","换肤:"+activity.getClass().getName());
        }
    }
}
