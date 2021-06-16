package com.dynamic.skin;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.List;

public class SkinFactory implements LayoutInflater.Factory2 {
    private static final List<SkinView> mSkinViewList = new ArrayList<>();
    private static final String TAG = "SkinFactory";
    private AppCompatDelegate mAppCompatDelegate;

    public SkinFactory(AppCompatDelegate mAppCompatDelegate) {
        this.mAppCompatDelegate = mAppCompatDelegate;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        //hook 获取view
        View view = mAppCompatDelegate.createView(parent, name, context, attrs);
        if (view != null) {
            Log.e(TAG, "onCreateView " + view.toString());
            for (int i = 0; i < attrs.getAttributeCount(); i++) {
                String attributeName = attrs.getAttributeName(i);
                String attributeValue = attrs.getAttributeValue(i);
                Log.i(TAG, attributeName + ":" + attributeValue);
            }
        }

        cachSkinView(view);
        return view;
    }

    private void cachSkinView(View view) {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return null;
    }

}
