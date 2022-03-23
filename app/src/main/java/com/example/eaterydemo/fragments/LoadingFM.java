package com.example.eaterydemo.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.FragmentGioithieuBinding;

import java.util.Random;


public class LoadingFM extends Fragment {
    FragmentGioithieuBinding fmIntroBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmIntroBinding = FragmentGioithieuBinding.inflate(getLayoutInflater());
        initClick();
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
}
