package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.eaterydemo.databinding.FragmentOwnerQuanlydonhangBinding;


public class CCH_ItemQuanLyDonHangFM extends Fragment {
    FragmentOwnerQuanlydonhangBinding fmBinding;
    NavController navController;
    View _view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentOwnerQuanlydonhangBinding.inflate(getLayoutInflater());
        _view = container;
        return fmBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClick();
        initNavController(_view);

        showProgressDialog(getContext(), "Đang tải dữ liệu");
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

    }

}
