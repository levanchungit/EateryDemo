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

import com.example.eaterydemo.databinding.FragmentQuenmatkhauDoimatkhaumoiBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.service.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuenMatKhau_DoiMatKhauMoiFM extends Fragment {
    FragmentQuenmatkhauDoimatkhaumoiBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentQuenmatkhauDoimatkhaumoiBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        fmBinding.btnDoiMatKhauQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _TenTK = DangNhapFM.TENTK;
                String _code = fmBinding.edtMaCodeQuenMatKhau.getText().toString().trim();
                String _matKhauMoi = fmBinding.edtNhapMatKhauMoiQuenMatKhau.getText().toString().trim();
                CapNhatMatKhau(_TenTK, _code, _matKhauMoi);
            }
        });
    }

    private void CapNhatMatKhau(String _TenTK, String _code, String _matKhauMoi) {
        showProgressDialog(getContext(), "Đang xác nhận...");
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.CapNhatMatKhau(_TenTK, _code, _matKhauMoi);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                Toast.makeText(requireContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                Log.e("CHECK QuenMK", message.getNotification());
                if (message.getStatus() == 1) {
                    NavDirections action = QuenMatKhau_DoiMatKhauMoiFMDirections.actionQuenMatKhauDoiMatKhauMoiFMToDangNhapFM();
                    navController.navigate(action);
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
