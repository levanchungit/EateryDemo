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

import com.example.eaterydemo.databinding.FragmentChinhsuaThongtinBinding;

public class ChinhSuaThongTinFM extends Fragment {
    FragmentChinhsuaThongtinBinding fmEditProfileBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmEditProfileBinding = FragmentChinhsuaThongtinBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);
        return fmEditProfileBinding.getRoot();
    }

    private void initNavController(View viewEditProfileBinding) {
        navController = Navigation.findNavController(viewEditProfileBinding);
    }

    private void initClick() {
        fmEditProfileBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ChinhSuaThongTinFMDirections.actionEditProfileFMToProfileFM();
                navController.navigate(action);
            }
        });
    }
}