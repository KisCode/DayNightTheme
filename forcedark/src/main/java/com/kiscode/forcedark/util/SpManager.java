package com.kiscode.forcedark.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SpManager {
    private static final String SP_NAME = "SpManager";
    private static final String KEY_IS_DARK = "key_is_dark";


    public static void put(Context context, boolean isDark) {
        SharedPreferences sh = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sh.edit().putBoolean(KEY_IS_DARK, isDark).apply();
    }

    public static boolean isDarkMode(Context context) {
        SharedPreferences sh = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sh.getBoolean(KEY_IS_DARK, false);
    }
} 