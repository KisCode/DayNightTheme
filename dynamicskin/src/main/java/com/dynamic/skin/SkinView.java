package com.dynamic.skin;

import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.List;

public class SkinView {
    private static final String TAG = "SkinView";
    private View view;
    private List<SkinPair> pairList;

    public SkinView(View view, List<SkinPair> pairList) {
        this.view = view;
        this.pairList = pairList;
    }

    public void applySkin() {
        Log.i(TAG, "applySkin:" + view.getClass().getName());
        for (SkinPair skinPair : pairList) {
            Log.w(TAG, skinPair.getAttributeName() + ":" + skinPair.getResId());
        }
    }
}
