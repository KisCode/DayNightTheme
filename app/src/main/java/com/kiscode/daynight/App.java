package com.kiscode.daynight;

import android.app.Application;

import com.dynamic.skin.SkinManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.init(this);
    }
}
