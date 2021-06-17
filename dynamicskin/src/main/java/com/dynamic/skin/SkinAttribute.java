package com.dynamic.skin;


import android.util.AttributeSet;
import android.util.Log;
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
    private static final String TAG = "SkinAttribute";

    static {
        mAttributes.add("textColor");
        mAttributes.add("background");
        mAttributes.add("tint");
    }

    private List<SkinView> mSkinViews = new ArrayList<>();

    public void look(View view, AttributeSet attrs) {
        if (view == null) {
            return;
        }
        Log.e(TAG, view.getClass().getName() + ":" + attrs.getAttributeCount());
        int count = attrs.getAttributeCount();
        List<SkinPair> skinPairList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String attributeName = attrs.getAttributeName(i);
            if (mAttributes.contains(attributeName)) {
                String attributeValue = attrs.getAttributeValue(i);
                Log.i(TAG, attributeName + ":" + attributeValue);

                //写死颜色值不做处理
                if (attributeValue.startsWith("#")) {
                    continue;
                }

                int resId;
                // 以 ？开头的表示使用 属性
                if (attributeValue.startsWith("?")) {
                    int attrId = Integer.parseInt(attributeValue.substring(1));
//                    resId = SkinThemeUtils.getResId(view.getContext(), new int[]{attrId})[0];
                    resId = 0;
                } else {
                    // 正常以 @ 开头
                    resId = Integer.parseInt(attributeValue.substring(1));
                }
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
