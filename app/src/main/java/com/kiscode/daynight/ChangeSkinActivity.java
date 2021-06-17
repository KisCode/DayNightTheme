package com.kiscode.daynight;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dynamic.skin.SkinManager;

import java.io.File;

public class ChangeSkinActivity extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ChangeSkinActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_skin);
    }

    public void changeSkin(View view) {
        String skinPath = getCacheDir().getAbsolutePath() + File.separator + "skin_red.apk";
        SkinManager.getInstance().load(skinPath);
    }
}