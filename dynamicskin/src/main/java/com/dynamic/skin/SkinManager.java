package com.dynamic.skin;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;

import com.dynamic.skin.support.SkinResourcesManager;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Observable;

/****
 * Description:换肤核心
 * Author:  keno
 * CreateDate: 2021/6/16 22:17
 */
public class SkinManager extends Observable {
    private static final String TAG = "SkinManager";

    private static SkinManager mInstance;
    private Application application;
    private SkinActivityLifecycleCallbacks activityLifecycleCallbacks;

    private SkinManager(Application application) {
        this.application = application;

        SkinResourcesManager.init(application);

        activityLifecycleCallbacks = new SkinActivityLifecycleCallbacks(this);
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public static void init(Application application) {
        if (mInstance == null) {
            synchronized (SkinManager.class) {
                if (mInstance == null) {
                    mInstance = new SkinManager(application);
                }
            }
        }
    }

    public static SkinManager getInstance() {
        return mInstance;
    }

    public void reset() {
        SkinResourcesManager.getInstance().reset();

        //通知刷新
        setChanged();
        notifyObservers(null);
    }

    /**
     * 加载制定皮肤
     *
     * @param skinPath 皮肤包路径
     */
    public void load(String skinPath) {
        if (TextUtils.isEmpty(skinPath) || !new File(skinPath).exists()) {
            //如果指定路径皮肤包文件不存在 则加载默认皮肤
            SkinResourcesManager.getInstance().reset();
        } else {
            //当前App Resources
            Resources appResources = application.getResources();

            try {
                //反射创建 皮肤包Resources
                AssetManager assetManager = AssetManager.class.newInstance();
                Method addAssetPathMethod = AssetManager.class.getMethod("addAssetPath", String.class);
                addAssetPathMethod.invoke(assetManager, skinPath);

                //创建皮肤 Resources
                Resources skinResources = new Resources(assetManager, appResources.getDisplayMetrics(), appResources.getConfiguration());

                //获取皮肤包名
                PackageManager packageManager = application.getPackageManager();
                PackageInfo info = packageManager.getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES);
                String packageName = info.packageName;

                SkinResourcesManager.getInstance().applySkin(skinResources, packageName);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }

        //通知刷新
        setChanged();
        notifyObservers(null);
    }

}
