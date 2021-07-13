package com.dynamic.skin;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class SkinFactory implements LayoutInflater.Factory2, Observer {
    private static final String TAG = "SkinFactory";
    private static final String[] mClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app.",
            "android.view."
    };

    //对应View两个参数构造函数
    private static final Class<?>[] mConstructorSignature = new Class[]{Context.class, AttributeSet.class};
    private static final HashMap<String, Constructor<? extends View>> mConstructorMap = new HashMap<>();

    private AppCompatDelegate mAppCompatDelegate;
    private SkinAttribute mSkinAttribute;

    public SkinFactory(AppCompatDelegate mAppCompatDelegate) {
        this.mAppCompatDelegate = mAppCompatDelegate;
        mSkinAttribute = new SkinAttribute();
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

//        Log.e(TAG, name);
        //hook 创建view
        View view = mAppCompatDelegate.createView(parent, name, context, attrs);


        if (view == null) {
            Log.i(TAG, "createSDKView " + name);
            view = createSDKView(name, context, attrs);
        }

        if (view == null) {
            Log.i(TAG, "createView " + name);
            view = createView(name, context, attrs);
        }

        if (view != null) {
            Log.e(TAG, "onCreateView " + view.toString());
            for (int i = 0; i < attrs.getAttributeCount(); i++) {
                String attributeName = attrs.getAttributeName(i);
                String attributeValue = attrs.getAttributeValue(i);
                Log.i(TAG, attributeName + ":" + attributeValue);
            }
        } else {
            Log.e(TAG, name + " is null");
        }

        mSkinAttribute.look(view, attrs);
        return view;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.i(TAG, "update");
        //接收消息
        mSkinAttribute.applySkin();//换肤
    }

    private View createSDKView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

        if (name.contains(".")) {
            return null;
        }

        for (String classPre : mClassPrefixList) {
            View view = createView(classPre + name, context, attrs);
            if (view != null) {
                return view;
            }
        }
        return null;
    }

    private View createView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        Constructor<? extends View> constructor = findConstructor(name, context);

        try {
            return constructor.newInstance(context, attrs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Constructor<? extends View> findConstructor(String name, Context context) {
        Constructor<? extends View> constructor = mConstructorMap.get(name);
        if (constructor == null) {
            try {
                Class<? extends View> clazz = context.getClassLoader().loadClass(name).asSubclass(View.class);
                constructor = clazz.getConstructor(mConstructorSignature);
                mConstructorMap.put(name, constructor);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return constructor;
    }

}
