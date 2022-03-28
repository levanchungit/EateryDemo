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

import com.example.eaterydemo.databinding.FragmentQuenmatkhauotpBinding;

public class QuenMatKhau_OTPFM extends Fragment {
    FragmentQuenmatkhauotpBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentQuenmatkhauotpBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        fmBinding.btnTiepTucQuenMatKhauOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = QuenMatKhau_OTPFMDirections.actionQuenMatKhauOTPFMToQuenMatKhauDoiMatKhauMoiFM();
                navController.navigate(action);
            }
        });
    }

}
