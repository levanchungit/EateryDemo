package com.example.eaterydemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.eaterydemo.databinding.FragmentThongtinBinding;


public class ThongTinFM extends Fragment {
    FragmentThongtinBinding fmProfileBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmProfileBinding = FragmentThongtinBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);
        return fmProfileBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        fmProfileBinding.ivThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ThongTinFMDirections.actionProfileFMToEditProfileFM();
                navController.navigate(action);
            }
        });
    }
}
