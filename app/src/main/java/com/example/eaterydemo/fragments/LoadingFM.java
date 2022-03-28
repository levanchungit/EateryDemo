package com.example.eaterydemo.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.cloudinary.android.MediaManager;
import com.example.eaterydemo.databinding.FragmentGioithieuBinding;

import java.util.HashMap;
import java.util.Map;


public class LoadingFM extends Fragment {
    FragmentGioithieuBinding fmIntroBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmIntroBinding = FragmentGioithieuBinding.inflate(getLayoutInflater());
        initClick();
        initCongif();
        initNavController(container);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NavDirections action = LoadingFMDirections.actionIntroFMToDangNhapFM();
                navController.navigate(action);
            }
        },2000);

        return fmIntroBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

    }

    // up-lấy file ảnh trên Dashboard cloundinary
    private void initCongif() {
        Map config = new HashMap();
        config.put("cloud_name", "dq7xnkfde");
        config.put("api_key", "527116776184176");
        config.put("api_secret", "5mYzdhfaQPuezS2Nz_G-raJ5LnY");
        MediaManager.init(requireContext(), config);

    }
}
