package demo.kiscode.daynight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import demo.kiscode.daynight.R;
import demo.kiscode.daynight.SpConstants;
import demo.kiscode.daynight.util.ActivityUtil;
import demo.kiscode.daynight.util.SpManager;

/**
 * Description: 详情页面重新设置夜间模式后会重新启动应用
 * Date : 2023/6/7 15:34
 **/
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        resisterSpChange();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listener != null) {
            SpManager.getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);
            listener = null;
        }
    }

    private void resisterSpChange() {
        SpManager.getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);
    }

    private void initView() {
        findViewById(R.id.btn_change).setOnClickListener(v -> {
            boolean isDayNight = SpManager.getBool(SpConstants.KEY_DAY_NIGHT, false);
            SpManager.putImmediately(SpConstants.KEY_DAY_NIGHT, !isDayNight);
        });
    }

    private SharedPreferences.OnSharedPreferenceChangeListener listener = (sharedPreferences, key) -> {
        if (SpConstants.KEY_DAY_NIGHT.equalsIgnoreCase(key)) {
            ActivityUtil.reStartApp(this);
        }
    };
}