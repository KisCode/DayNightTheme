package com.dynamic.skin.support;

import android.app.Application;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class SkinResourcesMananger {

    private static SkinResourcesMananger mInstance;
    private Application mApplication;

    private Resources mDefaultResources, mSkinResources;
    private String mPackageName;

    public SkinResourcesMananger(Application application) {
        this.mApplication = application;
        mDefaultResources = mApplication.getResources();
    }

    public static void init(Application application) {
        if (mInstance == null) {
            synchronized (SkinResourcesMananger.class) {
                if (mInstance == null) {
                    mInstance = new SkinResourcesMananger(application);
                }
            }
        }
    }

    public static SkinResourcesMananger getInstance() {
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

        int skinId = getIentifier(resId);
        if (skinId == 0) {
            return mDefaultResources.getColor(resId);
        }
        return mSkinResources.getColor(skinId);
    }

    public ColorStateList getColorStateList(int resId) {
        if (mSkinResources == null) {
            return mDefaultResources.getColorStateList(resId);
        }

        int skinId = getIentifier(resId);
        if (skinId == 0) {
            return mDefaultResources.getColorStateList(resId);
        }
        return mSkinResources.getColorStateList(skinId);
    }

    public Drawable getDrawable(int resId) {
        if (mSkinResources == null) {
            return mDefaultResources.getDrawable(resId);
        }

        int skinId = getIentifier(resId);
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

    public int getIentifier(int resId) {
        if (mSkinResources == null) {
            return resId;
        }
        //根据resId获取对应的资源名称
        String resName = mDefaultResources.getResourceEntryName(resId);
        String resType = mDefaultResources.getResourceTypeName(resId);
        //根据名字和类型 从替换skin皮肤库内找到对应资源名称的ID
        return mSkinResources.getIdentifier(resName, resType, mPackageName);
    }
}