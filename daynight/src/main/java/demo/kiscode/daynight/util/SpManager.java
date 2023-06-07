package demo.kiscode.daynight.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

/****
 * Description: SharedPreferences管理类
 * Author:  keno
 * CreateDate: 2021/1/20 20:18
 */
public class SpManager {
    private static final String name = "SpManager";
    private static SharedPreferences sharedPreferences;

    /**
     * 需要再App onCreate 中初始化
     *
     * @param context
     */
    public static void init(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static float getFloat(String key) {
        return (float) get(key, 0f);
    }

    public static float getFloat(String key, float defaultValue) {
        return (float) get(key, defaultValue);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        return (int) get(key, defaultValue);
    }

    public static long getLong(String key) {
        return getLong(key, 0L);
    }

    public static long getLong(String key, long defaultValue) {
        return (long) get(key, defaultValue);
    }

    public static boolean getBool(String key, boolean defaultValue) {
        return (boolean) get(key, defaultValue);
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultValue) {
        return (String) get(key, defaultValue);
    }

    public static boolean contain(String key) {
        return sharedPreferences.contains(key);
    }

    public static Object get(String key, @NonNull Object defaultValue) {
        if (sharedPreferences == null) {
            throw new IllegalArgumentException("SpManager not init!");
        }
        if (defaultValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        } else {
            return sharedPreferences.getString(key, (String) defaultValue);
        }
    }

    public static void put(String key, Object value) {
        if (sharedPreferences == null) {
            throw new IllegalArgumentException("SpManager not init!");
        }

        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else {
            edit.putString(key, (String) value);
        }
        edit.apply();
    }


    public static void putImmediately(String key, Object value) {
        if (sharedPreferences == null) {
            throw new IllegalArgumentException("SpManager not init!");
        }

        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else {
            edit.putString(key, (String) value);
        }
        edit.commit();
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}