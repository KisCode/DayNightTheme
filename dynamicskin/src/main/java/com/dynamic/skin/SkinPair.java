package com.dynamic.skin;


/**** 
 * Description:
 * Author:  keno
 * CreateDate: 2021/6/16 21:40
 */

public class SkinPair {
    private String attributeName;
    private int resId;

    public String getAttributeName() {
        return attributeName;
    }

    public int getResId() {
        return resId;
    }

    public SkinPair(String attributeName, int resId) {
        this.attributeName = attributeName;
        this.resId = resId;
    }
}
