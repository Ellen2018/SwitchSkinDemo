package com.yalemang.switchskindemo.skin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class SkinLayoutFactory implements LayoutInflater.Factory2 {

    //系统自带的控件名包名路径
    private static final String[] systemViewPackage = {
            "androidx.widget.",
            "androidx.view.",
            "androidx.webkit.",
            "android.widget.",
            "android.view.",
            "android.webkit."
    };

    private static final Class[] mConstructorSignature = new Class[]{Context.class,AttributeSet.class};
    private static final HashMap<String, Constructor<? extends View>> mConstructor = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View view = onCreateViewFromTag(name,context,attributeSet);
        if(view == null){
            view = onCreateView(name, context, attributeSet);
        }
        return view;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        Constructor<? extends View> constructor = mConstructor.get(name);
        View view = null;
        if(constructor == null){
            try {
                Class<? extends View> viewClass = context.getClassLoader().loadClass(name).asSubclass(View.class);
                constructor = viewClass.getConstructor(mConstructorSignature);
                mConstructor.put(name,constructor);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        if(constructor != null){
            try {
                view = constructor.newInstance(context,attributeSet);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    private View onCreateViewFromTag(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attributeSet){
        if(name.indexOf(".") > 0){

        }
        View view = null;
        for(String packageName:systemViewPackage){
            view = onCreateView(packageName+name,context,attributeSet);
            if(view != null){
                break;
            }
        }
        return view;
    }
}
