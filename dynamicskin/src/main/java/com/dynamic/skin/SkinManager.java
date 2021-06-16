package com.dynamic.skin;


import android.app.Application;

import java.util.Observable;

/****
 * Description:
 * Author:  keno
 * CreateDate: 2021/6/16 22:17
 */

public class SkinManager extends Observable {
    private static final String TAG = "SkinManager";

    private static SkinManager mInstance;
    private Application mContext;

    private SkinManager(Application mContext) {
        this.mContext = mContext;
    }

    public static void init(Application application) {
        if (mInstance == null) {
            synchronized (SkinManager.class) {
                mInstance = new SkinManager(application);
            }
        }
    }

    public static SkinManager getInstance() {
        return mInstance;
    }

    public void load() {

    }

    public void change() {
        setChanged();
        notifyObservers(null);

    }

}
