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

import com.example.eaterydemo.databinding.FragmentQuenmatkhauBinding;

public class QuenMatKhauFM extends Fragment {
    FragmentQuenmatkhauBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentQuenmatkhauBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        fmBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = QuenMatKhauFMDirections.actionQuenMatKhauFMToDangNhapFM();
                navController.navigate(action);
            }
        });

        fmBinding.btnGuiKiemTraQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1. nếu là email thì toast thông báo kêu ktra email để rs mk
                //2. Nếu là sdt là chuyển màn hình tiếp => nhập mã OTP => đổi mật khẩu
                NavDirections action = QuenMatKhauFMDirections.actionQuenMatKhauFMToQuenMatKhauOTPFM();
                navController.navigate(action);
            }
        });
    }

}
