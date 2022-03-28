package com.example.eaterydemo.fragments;


import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.content.Intent;
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

import com.example.eaterydemo.activities.DrawerLayoutActivity;
import com.example.eaterydemo.databinding.FragmentDangnhapBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.service.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapFM extends Fragment {
    FragmentDangnhapBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentDangnhapBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

        fmBinding.btnLoginDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _TenTK = fmBinding.edtEmailDangNhap.getText().toString().trim();
                String _MatKhau = fmBinding.edtMatKhauDangNhap.getText().toString().trim();
                DangNhap(_TenTK, _MatKhau);
            }
        });

        fmBinding.tvDangKyDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = DangNhapFMDirections.actionDangNhapFMToDangKyFM();
                navController.navigate(action);
            }
        });

        fmBinding.tvQuenMatKhauDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = DangNhapFMDirections.actionDangNhapFMToQuenMatKhauFM();
                navController.navigate(action);
            }
        });
    }

    private void DangNhap(String _TenTK, String _MatKhau) {
        showProgressDialog(getContext(),"Đang đăng nhập...");
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.DangNhap(_TenTK, _MatKhau);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                Log.e("LOGIN",message.getNotification());
                if (message.getStatus() == 1) {
                    startActivity(new Intent(requireContext(), DrawerLayoutActivity.class));
                }
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
