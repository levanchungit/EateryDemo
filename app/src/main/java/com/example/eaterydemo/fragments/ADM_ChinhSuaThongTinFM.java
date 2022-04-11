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

import com.bumptech.glide.Glide;
import com.example.eaterydemo.activities.DrawerLayoutActivity;
import com.example.eaterydemo.databinding.FragmentChinhsuaThongtinBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ADM_ChinhSuaThongTinFM extends Fragment {
    FragmentChinhsuaThongtinBinding fmEditProfileBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmEditProfileBinding = FragmentChinhsuaThongtinBinding.inflate(getLayoutInflater());
        initClick();

        GetThongTin(ADM_ChinhSuaThongTinFMArgs.fromBundle(getArguments()).getTenTk());
        initNavController(container);
        return fmEditProfileBinding.getRoot();
    }

    private void initNavController(View viewEditProfileBinding) {
        navController = Navigation.findNavController(viewEditProfileBinding);
    }

    private void initClick() {

        fmEditProfileBinding.btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String _TenTK =  DangNhapFM.TENTK;
                String _HoTen = fmEditProfileBinding.edtFullName.getText().toString().trim();
                String _SDT = fmEditProfileBinding.edtSDT.getText().toString().trim();
                String _DiaChi = fmEditProfileBinding.edtDiaChi.getText().toString().trim();
                ChinhSuaThongTin(_TenTK, _HoTen,_SDT,_DiaChi);
                NavDirections action = ADM_ChinhSuaThongTinFMDirections.actionADMChinhSuaThongTinFMToADMQuanTriHeThongFM();
                navController.navigate(action);
                dismissProgressDialog();
            }
        });
    }

    private void GetThongTin(String _TenTK) {
        showProgressDialog(getContext(), "Đang xử lý");
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetTaiKhoanTheoTenTK(_TenTK);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                TaiKhoan taikhoan = (TaiKhoan) response.body();
                fmEditProfileBinding.tvEmail.setText(taikhoan.getTenTK());
                fmEditProfileBinding.edtSDT.setText(taikhoan.getSDT());
                fmEditProfileBinding.edtFullName.setText(taikhoan.getHoTen());
                fmEditProfileBinding.edtDiaChi.setText(taikhoan.getDiaChi());
                Glide.with(getContext()).load(taikhoan.getHinhAnh()).centerCrop().into(fmEditProfileBinding.ivAvatarAdminChinhSuathongTin);
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ChinhSuaThongTin(String _TenTK, String _HoTen,String _SDT,String _DiaChi) {
        showProgressDialog(getContext(),"Đang xác nhân");
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.ChinhSuaThongTin(_TenTK, _HoTen,_SDT, _DiaChi);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                Log.e("LOGIN",message.getNotification());
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
