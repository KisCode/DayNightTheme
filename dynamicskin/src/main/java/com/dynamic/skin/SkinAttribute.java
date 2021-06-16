package com.dynamic.skin;


import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/****
 * Description:
 * Author:  keno
 * CreateDate: 2021/6/16 21:33
 */

public class SkinAttribute extends Observable {
    private static final List<String> mAttributes = new ArrayList<>();
    private List<SkinView> mSkinViews = new ArrayList<>();

    static {
        mAttributes.add("textColor");
        mAttributes.add("background");
    }

    public void look(View view, AttributeSet attrs) {
        int count = attrs.getAttributeCount();
        List<SkinPair> skinPairList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String attributeName = attrs.getAttributeName(i);
            if (mAttributes.contains(attributeName)) {
                String attributeValue = attrs.getAttributeValue(i);

                int resId = Integer.parseInt(attributeValue);
                SkinPair skinPair = new SkinPair(attributeName, resId);
                skinPairList.add(skinPair);
            }
        }

        if (!skinPairList.isEmpty()) {
            SkinView skinView = new SkinView(view, skinPairList);
            mSkinViews.add(skinView);
        }

    }

    /***
     * 执行换肤
     */
    public void applySkin() {
        for (SkinView skinView : mSkinViews) {
            skinView.applySkin();
        }
    }


}
