package com.dynamic.skin;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Observable;

public class SkinActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private Observable mObservable;

    public SkinActivityLifecycleCallbacks(Observable observable) {
        this.mObservable = observable;
    }

    @Override
    public void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        if (activity instanceof AppCompatActivity) {
            LayoutInflater layoutInflater = LayoutInflater.from(activity);
            AppCompatDelegate appCompatDelegate = ((AppCompatActivity) activity).getDelegate();
            SkinFactory skinFactory = new SkinFactory(appCompatDelegate);
            layoutInflater.setFactory2(skinFactory);
            mObservable.addObserver(skinFactory);
        }
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
              /*  if(activity instanceof AppCompatActivity) {
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
                }*/
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
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
