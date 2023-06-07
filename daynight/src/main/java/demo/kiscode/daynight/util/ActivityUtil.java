package demo.kiscode.daynight.util;

import android.content.Context;
import android.content.Intent;

/**
 * Description:
 * Date : 2023/6/7 14:40
 **/
public class ActivityUtil {


    /**
     * 重启App
     * @param context
     */
    public static void reStartApp(Context context) {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());

    }
}
