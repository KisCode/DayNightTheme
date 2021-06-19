package com.dynamic.skin.support;


import android.content.Context;
import android.content.res.TypedArray;

/****
 * Description:
 * Author:  keno
 * CreateDate: 2021/6/19 9:54
 */

public class SkinThemeUtils {

    /***
     * 获取theme主题属性中的的资源Id
     * @param context
     * @param attrs
     * @return
     */
    public static int[] getResId(Context context, int[] attrs) {
        int[] resIds = new int[attrs.length];
        TypedArray a = context.obtainStyledAttributes(attrs);
        for (int i = 0; i < attrs.length; i++) {
            resIds[i] = a.getResourceId(i, 0);
        }
        a.recycle();
        return resIds;
    }
}
