package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.databinding.FragmentAdminAddnhahangBinding;
import com.example.eaterydemo.databinding.FragmentChinhsuaQuanlynhahangBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.service.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ADM_AddNhaHangFM extends Fragment {
    FragmentAdminAddnhahangBinding fmBinding;
    NavController navController;
    View _view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentAdminAddnhahangBinding.inflate(getLayoutInflater());
        _view = container;
        return fmBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClick();
        initNavController(_view);

//        showProgressDialog(getContext(), "Đang tải dữ liệu");

    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

    }
}
