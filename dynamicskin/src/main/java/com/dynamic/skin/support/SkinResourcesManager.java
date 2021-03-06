package com.dynamic.skin.support;

import android.app.Application;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class SkinResourcesManager {

    private static SkinResourcesManager mInstance;
    private Application mApplication;

    private Resources mDefaultResources, mSkinResources;
    private String mPackageName;

    public SkinResourcesManager(Application application) {
        this.mApplication = application;
        mDefaultResources = mApplication.getResources();
    }

    public static void init(Application application) {
        if (mInstance == null) {
            synchronized (SkinResourcesManager.class) {
                if (mInstance == null) {
                    mInstance = new SkinResourcesManager(application);
                }
            }
        }
    }

    public static SkinResourcesManager getInstance() {
        return mInstance;
    }

    public void reset() {
        mSkinResources = null;
        mPackageName = null;
    }

    public void applySkin(Resources skinResource, String skinPackageName) {
        mSkinResources = skinResource;
        mPackageName = skinPackageName;
    }

    public int getColor(int resId) {
        if (mSkinResources == null) {
            return mDefaultResources.getColor(resId);
        }

        int skinId = getIdentifier(resId);
        if (skinId == 0) {
            return mDefaultResources.getColor(resId);
        }
        return mSkinResources.getColor(skinId);
    }

    public ColorStateList getColorStateList(int resId) {
        if (mSkinResources == null) {
            return mDefaultResources.getColorStateList(resId);
        }

        int skinId = getIdentifier(resId);
        if (skinId == 0) {
            return mDefaultResources.getColorStateList(resId);
        }
        return mSkinResources.getColorStateList(skinId);
    }

    public Drawable getDrawable(int resId) {
        if (mSkinResources == null) {
            return mDefaultResources.getDrawable(resId);
        }

        int skinId = getIdentifier(resId);
        if (skinId == 0) {
            return mDefaultResources.getDrawable(resId);
        }
        return mSkinResources.getDrawable(skinId);
    }

    public Object getBackground(int resId){
        String resourceTypeName = mDefaultResources.getResourceTypeName(resId);
        if("color".equals(resourceTypeName)){
            return getColor(resId);
        }else{
            return getDrawable(resId);
        }
    }

    public int getIdentifier(int resId) {
        if (mSkinResources == null) {
            return resId;
        }
        //??????resId???????????????????????????
        String resName = mDefaultResources.getResourceEntryName(resId);
        String resType = mDefaultResources.getResourceTypeName(resId);
        //????????????????????? ?????????skin???????????????????????????????????????ID
        return mSkinResources.getIdentifier(resName, resType, mPackageName);
    }
}