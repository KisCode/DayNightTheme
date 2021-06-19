package com.dynamic.skin;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.lang.reflect.Field;
import java.util.Observable;

/**
 * Description: Activity声明周期监听回调
 * <p>
 * 初始化SkinManager时注册，在Activity的Oncreate方法回调中 设置自定义SkinFactory替代系统，从而实接管创建view的过程
 * Author: keno
 * CreateDate: 2021/6/19 17:15
 */

public class SkinActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "SkinLifecycle";
    private Observable mObservable;

    public SkinActivityLifecycleCallbacks(Observable observable) {
        this.mObservable = observable;
    }

    @Override
    public void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityPreCreated " + activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //在Android 9.0以下设备不会触发此方法的回调
            if (activity instanceof AppCompatActivity) {
                LayoutInflater layoutInflater = LayoutInflater.from(activity);
                AppCompatDelegate appCompatDelegate = ((AppCompatActivity) activity).getDelegate();
                SkinFactory skinFactory = new SkinFactory(appCompatDelegate);
                layoutInflater.setFactory2(skinFactory);
                mObservable.addObserver(skinFactory);
            }
        }
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated " + activity);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            if (activity instanceof AppCompatActivity) {
                LayoutInflater layoutInflater = LayoutInflater.from(activity);
                try {
                    //系统默认 LayoutInflater只能设置一次factory，所以利用反射解除限制
                    Field mFactorySet = LayoutInflater.class.getDeclaredField("mFactorySet");
                    mFactorySet.setAccessible(true);
                    mFactorySet.setBoolean(layoutInflater, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                AppCompatDelegate appCompatDelegate = ((AppCompatActivity) activity).getDelegate();
                SkinFactory skinFactory = new SkinFactory(appCompatDelegate);
                layoutInflater.setFactory2(skinFactory);
                mObservable.addObserver(skinFactory);
            }
        }

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.i(TAG, "onActivityStarted");
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.i(TAG, "onActivityResumed");

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.i(TAG, "onActivityPaused");
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.i(TAG, "onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        Log.i(TAG, "onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.i(TAG, "onActivityDestroyed");

    }
}
