package com.kiscode.daynight;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kiscode.daynight.util.ThemeHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
        initViews();
        initNightMode();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    private void initViews() {
        TextView tvTime = findViewById(R.id.tv_current_time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        tvTime.setText(sdf.format(Calendar.getInstance().getTimeInMillis()));

        Switch darkModeSwitch = findViewById(R.id.switch_dark_mode);
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                //强制暗黑模式
                ThemeHelper.applyTheme(ThemeHelper.DARK_MODE);
            } else {
                //跟随系统
                ThemeHelper.applyTheme(ThemeHelper.DEFAULT_MODE);
            }
        });
    }

    private void initNightMode() {
        int uiMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        Log.i(TAG, "uiMode:" + uiMode);
        switch (uiMode) {
            case Configuration.UI_MODE_NIGHT_NO:    //使用非夜间模式的资源

                break;
            case Configuration.UI_MODE_NIGHT_YES:    //使用夜间模式的资源

                break;
            case Configuration.UI_MODE_NIGHT_UNDEFINED:    //没有设置模式

                break;
        }
    }


    public void open(View view) {
        NavigatorActivity.start(this);
    }

    //动态换肤
    public void openSkin(View view) {
        ChangeSkinActivity.start(this);
    }
}