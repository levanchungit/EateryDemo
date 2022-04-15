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

import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.FragmentSplashBinding;

import java.util.List;


public class SplashFM extends Fragment {
    FragmentSplashBinding fmSplashBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmSplashBinding = FragmentSplashBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NavDirections action = SplashFMDirections.actionSplashFMToIntroFM();
                navController.navigate(action);
            }
        }, 3000);


        return fmSplashBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

    }
}
