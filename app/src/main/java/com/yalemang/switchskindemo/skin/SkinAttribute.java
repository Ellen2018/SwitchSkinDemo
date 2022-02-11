package com.yalemang.switchskindemo.skin;

import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yalemang.switchskindemo.SwitchSkinActivity;
import com.yalemang.switchskindemo.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SkinAttribute {

    private static final List<String> ATTRIBUTE = new ArrayList<>();

    static {
        ATTRIBUTE.add("background");
        ATTRIBUTE.add("src");

        ATTRIBUTE.add("textColor");
        ATTRIBUTE.add("SkinTypeface");
    }

    public void loadView(View view, AttributeSet attributeSet) {
        for (int i = 0; i < attributeSet.getAttributeCount(); i++) {
            String attributeName = attributeSet.getAttributeName(i);
           if(ATTRIBUTE.contains(attributeName)){
               String attributeValue = attributeSet.getAttributeValue(i);
               if(attributeValue.startsWith("#")){
                  //固定的Color值，无需修改
               }
               int resId = 0;
               //判断前缀是否为？
               int attrId = Integer.parseInt(attributeValue.substring(1));
               if(attributeValue.startsWith("?")){
                   int[] array = {attrId};
                   resId = SkinThemeUtils.getResId(view.getContext(),array)[0];
               }else {
                   resId = attrId;
               }
               if(resId != 0){
                   Log.d("Ellen2018","view = "+view.getClass().getName());
                   Log.d("Ellen2018","attributeName = "+ attributeName);
                   Log.d("Ellen2018","attributeValue = "+ attributeValue);
                   Log.d("Ellen2018","resId = "+ resId);

                   //拿到空壳App资源
                   File skinFile = new File(view.getContext().getCacheDir(),"skin_test_blue.apk");
                   if(!skinFile.exists()){
                       //复制文件
                       FileUtils.copyFileFromAssets(view.getContext(), "skin_test_blue.apk",
                               view.getContext().getCacheDir().getAbsolutePath(), "skin_test_blue.apk");
                   }
                   SkinLoadApkPath skinLoadApkPath = new SkinLoadApkPath();
                   skinLoadApkPath.loadEmptyApkPath(skinFile.getAbsolutePath());
                   Resources skinResource = skinLoadApkPath.getSkinResource();
                   if(attributeName.equals("textColor")){
                       TextView textView = (TextView) view;
                       textView.setTextColor(skinResource.getColorStateList(resId));
                   }
               }
           }
        }

    }

}
