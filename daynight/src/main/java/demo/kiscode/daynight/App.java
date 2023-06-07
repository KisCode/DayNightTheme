package demo.kiscode.daynight;

import android.app.Application;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import demo.kiscode.daynight.util.SpManager;

/**
 * Description: Application
 * Date : 2023/6/6 15:30
 **/
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SpManager.init(this);

        boolean isDayNight = SpManager.getBool(SpConstants.KEY_DAY_NIGHT, false);
        Log.i("isDayNight","isDayNight "+isDayNight);
        AppCompatDelegate.setDefaultNightMode(isDayNight ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }
}
