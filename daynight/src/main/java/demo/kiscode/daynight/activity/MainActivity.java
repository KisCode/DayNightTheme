package demo.kiscode.daynight.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.textfield.TextInputEditText;

import demo.kiscode.daynight.R;
import demo.kiscode.daynight.SpConstants;
import demo.kiscode.daynight.util.SpManager;

public class MainActivity extends AppCompatActivity {
    private Button btnChange, btnDetail;
    private TextInputEditText textInputEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChange = findViewById(R.id.btn_change);
        btnDetail = findViewById(R.id.btn_detail);
        textInputEt = findViewById(R.id.textInputEt);

        textInputEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 3) {
                    textInputEt.setError("输入内容超过上限");
                } else {
                    textInputEt.setError(null);
                }
            }
        });

        btnChange.setOnClickListener(v -> {
/*
            int localNightMode = getDelegate().getLocalNightMode();
            if (localNightMode != AppCompatDelegate.MODE_NIGHT_YES) {
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            */

            boolean isDayNight = SpManager.getBool(SpConstants.KEY_DAY_NIGHT, false);
            SpManager.putImmediately(SpConstants.KEY_DAY_NIGHT, !isDayNight);
            AppCompatDelegate.setDefaultNightMode(!isDayNight ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        });

        btnDetail.setOnClickListener(view -> startActivity(new Intent(this, DetailActivity.class)));
    }
}