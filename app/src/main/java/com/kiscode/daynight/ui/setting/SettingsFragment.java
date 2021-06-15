package com.kiscode.daynight.ui.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kiscode.daynight.databinding.FragmentNotificationsBinding;
import com.kiscode.daynight.util.ThemeHelper;

public class SettingsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        binding.switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                //强制暗黑模式
                ThemeHelper.applyTheme(ThemeHelper.DARK_MODE);
            } else {
                //跟随系统
                ThemeHelper.applyTheme(ThemeHelper.DEFAULT_MODE);
            }
        });
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}