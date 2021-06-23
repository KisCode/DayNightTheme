package com.kiscode.forcedark.util;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

/**
 * AppCompatDelegate 获取5种声色模式配置
 * MODE_NIGHT_AUTO_BATTERY 低电量模式自动开启深色模式
 * MODE_NIGHT_FOLLOW_SYSTEM 跟随系统开启和关闭深色模式（默认）
 * MODE_NIGHT_NO 强制使用notnight资源，表示非深色模式
 * MODE_NIGHT_YES 强制使用night资源
 * MODE_NIGHT_UNSPECIFIED 配合 setLocalNightMode(int)) 使用，表示由Activity通过AppCompactActivity.getDelegate()来单独设置页面的深色模式，不设置全局模式
 */
public class ThemeUtil {

    private static final String LIGHT_MODE = "light";
    private static final String DARK_MODE = "dark";
    private static final String DEFAULT_MODE = "default";

    private static void applyTheme(@NonNull String themePref) {
        switch (themePref) {
            case LIGHT_MODE: {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            }
            case DARK_MODE: {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            }
            default: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                }
                break;
            }
        }
    }

    public static void applyDarkTheme() {
        applyTheme(DARK_MODE);
    }

    public static void applyLightTheme() {
        applyTheme(LIGHT_MODE);
    }

    /***
     * 是否夜间模式
     * @return true 夜间模式
     */
    public static boolean isNightModeEnabled(Context context) {
        int uiMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return uiMode == Configuration.UI_MODE_NIGHT_YES;
    }
}
