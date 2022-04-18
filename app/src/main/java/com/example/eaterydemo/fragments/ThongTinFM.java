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
import com.example.eaterydemo.R;
import com.example.eaterydemo.activities.DrawerLayoutActivity;
import com.example.eaterydemo.databinding.FragmentThongtinBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThongTinFM extends Fragment {
    FragmentThongtinBinding fmProfileBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmProfileBinding = FragmentThongtinBinding.inflate(getLayoutInflater());



        initClick();
        GetThongTin(DangNhapFM.TENTK);
        initNavController(container);
        return fmProfileBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        fmProfileBinding.ivDonHangCuaBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ThongTinFMDirections.actionMenuThongTinToDonHangFM();
                navController.navigate(action);
            }
        });
        fmProfileBinding.ivPhuongThucThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ThongTinFMDirections.actionMenuThongTinToPhuongThucThanhToanFM();
                navController.navigate(action);
            }
        });

        fmProfileBinding.ivVeChungToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ThongTinFMDirections.actionMenuThongTinToVeChungToiFM();
                navController.navigate(action);
            }
        });

        fmProfileBinding.ivThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ThongTinFMDirections.actionMenuThongTinToEditProfileFM();
                navController.navigate(action);
            }
        });

        fmProfileBinding.ivDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ThongTinFMDirections.actionMenuThongTinToDoiMatKhauFM();
                navController.navigate(action);
            }
        });

        fmProfileBinding.ivDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //tắt bottom navigation
        BottomNavigationView navbar = getActivity().findViewById(R.id.navBot);
        navbar.setVisibility(View.VISIBLE);
    }

    private void GetThongTin(String _TenTK) {
        showProgressDialog(getContext(), "Đang xử lý");
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetTaiKhoanTheoTenTK(_TenTK);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                TaiKhoan taikhoan = (TaiKhoan) response.body();
                if (taikhoan != null) {
                    fmProfileBinding.ivTen.setText(taikhoan.getHoTen());
                    Glide.with(getContext()).load(taikhoan.getHinhAnh()).centerCrop().into(fmProfileBinding.ivAvatarProfile);
                    dismissProgressDialog();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
