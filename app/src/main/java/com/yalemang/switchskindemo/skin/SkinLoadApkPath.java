package com.yalemang.switchskindemo.skin;

import android.content.res.AssetManager;
import android.content.res.Resources;

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
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, apkPath);

            Resources appResources = SkinManager.getInstance().getApplication().getResources();
            skinResources = new Resources(assetManager,
                    appResources.getDisplayMetrics(), appResources.getConfiguration());

            //skinResources就是我们替换的皮肤包Resources

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
