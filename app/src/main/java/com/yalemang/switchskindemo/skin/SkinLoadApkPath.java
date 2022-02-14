package com.yalemang.switchskindemo.skin;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SkinLoadApkPath {

    private Resources skinResources;

    public Resources getSkinResource(){
        return skinResources;
    }

    /**
     * 加载空壳Apk资源
     *
     * @param apkPath
     */
    public void loadEmptyApkPath(String apkPath) {
        try {
            Resources appResources = SkinManager.getInstance().getApplication().getResources();
            if(SkinManager.getInstance().isDefaultSkin()){
                //使用默认资源
                skinResources = appResources;
            }else {
                AssetManager assetManager = AssetManager.class.newInstance();
                Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
                addAssetPath.invoke(assetManager, apkPath);

                //使用空壳Apk资源
                skinResources = new Resources(assetManager,
                        appResources.getDisplayMetrics(), appResources.getConfiguration());
            }
        } catch (Exception e) {
            Log.d("Ellen2018","发生异常");
        }
    }
}
