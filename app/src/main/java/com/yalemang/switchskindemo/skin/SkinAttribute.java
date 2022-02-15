package com.yalemang.switchskindemo.skin;

import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.yalemang.switchskindemo.SwitchSkinActivity;
import com.yalemang.switchskindemo.utils.FileUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SkinAttribute {

    //过滤出皮肤需要的属性
    private static final List<String> ATTRIBUTE = new ArrayList<>();

    static {
        ATTRIBUTE.add("background");
        ATTRIBUTE.add("src");

        ATTRIBUTE.add("textColor");
        ATTRIBUTE.add("SkinTypeface");

        //TabLayout
        ATTRIBUTE.add("tabIndicatorColor");
        ATTRIBUTE.add("tabSelectedTextColor");
    }

    public void loadView(View view, AttributeSet attributeSet) {
        for (int i = 0; i < attributeSet.getAttributeCount(); i++) {
            String attributeName = attributeSet.getAttributeName(i);
            if (ATTRIBUTE.contains(attributeName)) {
                String attributeValue = attributeSet.getAttributeValue(i);
                if (attributeValue.startsWith("#")) {
                    //固定的Color值，无需修改
                } else {
                    int resId = 0;
                    //判断前缀是否为？
                    int attrId = Integer.parseInt(attributeValue.substring(1));
                    if (attributeValue.startsWith("?")) {
                        int[] array = {attrId};
                        resId = SkinThemeUtils.getResId(view.getContext(), array)[0];
                    } else {
                        resId = attrId;
                    }
                    if (resId != 0) {
                        String skinName = SkinManager.getInstance().getCurrentSkin();
                        File skinFile = new File(view.getContext().getCacheDir(), skinName);
                        //拿到空壳App资源
                        if (!SkinManager.getInstance().isDefaultSkin()) {
                            //如果皮肤包不存在，那么先从asset里进行拷贝到SD卡【模拟从服务器下载过程】
                            if (!skinFile.exists()) {
                                //复制文件
                                FileUtils.copyFileFromAssets(view.getContext(), skinName,
                                        view.getContext().getCacheDir().getAbsolutePath(), skinName);
                            }
                        }
                        SkinLoadApkPath skinLoadApkPath = new SkinLoadApkPath();
                        skinLoadApkPath.loadEmptyApkPath(skinFile.getAbsolutePath());
                        Resources skinResource = skinLoadApkPath.getSkinResource();
                        if (attributeName.equals("textColor")) {
                            TextView textView = (TextView) view;
                            textView.setTextColor(skinResource.getColorStateList(resId));
                        }
                        if (attributeName.equals("background")) {
                            view.setBackgroundColor(skinResource.getColor(resId));
                        }
                        if (attributeName.equals("tabIndicatorColor")) {
                            //TabLayout下划线颜色
                            TabLayout tabLayout = (TabLayout) view;
                            tabLayout.setSelectedTabIndicatorColor(skinResource.getColor(resId));
                        }
                        if (attributeName.equals("tabSelectedTextColor")) {
                            //TabLayout选中文本颜色
                            TabLayout tabLayout = (TabLayout) view;
                            tabLayout.setTabTextColors(Color.BLACK, skinResource.getColor(resId));
                        }
                    }
                }
            }
        }

    }

}
