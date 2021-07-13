package com.dynamic.skin;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.dynamic.skin.support.SkinResourcesManager;

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
            switch (skinPair.getAttributeName()) {
                case "background":
                    Object skinBackground = SkinResourcesManager.getInstance().getBackground(skinPair.getResId());
                    if (skinBackground == null) {
                        return;
                    }
                    if (skinBackground instanceof Integer) {
                        view.setBackgroundColor((int) skinBackground);
                    } else {
                        ViewCompat.setBackground(view, (Drawable) skinBackground);
                    }
                    break;
                case "textColor":
                    ColorStateList skinColor = SkinResourcesManager.getInstance()
                            .getColorStateList(skinPair.getResId());
                    ((TextView) view).setTextColor(skinColor);
                    break;
                case "tint":
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ColorStateList tintColorStateList = SkinResourcesManager.getInstance().getColorStateList(skinPair.getResId());
                        ((ImageView) view).setImageTintList(tintColorStateList);
                    }
                    break;
            }
        }
    }
}
