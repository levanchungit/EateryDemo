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

import com.example.eaterydemo.databinding.FragmentSplashBinding;
import com.example.eaterydemo.databinding.FragmentTrangchuBinding;


public class TrangChuFM extends Fragment {
    FragmentTrangchuBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentTrangchuBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NavDirections action = TrangChuFMDirections.actionHomeFMToProfileFM();
                navController.navigate(action);
            }
        }, 3000);


        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

    }
}
