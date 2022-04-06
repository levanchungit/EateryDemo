package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.AdminQuanLyCuaHangAdapter;
import com.example.eaterydemo.adapter.DonHangChiTietAdapter;
import com.example.eaterydemo.databinding.FragmentAdminQuantrihethongBinding;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.DonHangChiTiet;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ADM_QuanTriHeThongFM extends Fragment {
    FragmentAdminQuantrihethongBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentAdminQuantrihethongBinding.inflate(getLayoutInflater());
        initNavController(container);
        initClick();
        GetInfoAdmin();
        return fmBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    private void initClick() {

    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void GetInfoAdmin(){
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetTaiKhoanTheoTenTK(DangNhapFM.TENTK);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                TaiKhoan tk = (TaiKhoan) response.body();
                Glide.with(getContext()).load(tk.getHinhAnh()).centerCrop().placeholder(R.drawable.img_error).into(fmBinding.ivImageAdmin);
                fmBinding.tvTenAdmin.setText(tk.getHoTen());
                //Chuyển sang quản lý cửa hàng
                fmBinding.llQuanLyCuaHangAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NavDirections action = ADM_QuanTriHeThongFMDirections.actionADMQuanTriHeThongFMToAdminQuanLyCuaHangFM();
                        navController.navigate(action);
                    }
                });

                //CHuyển sang quản lý tài khoản
                fmBinding.llQuanLyTaiKhoanAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NavDirections action = ADM_QuanTriHeThongFMDirections.actionADMQuanTriHeThongFMToAdminQuanLyTaiKhoanFM();
                        navController.navigate(action);
                    }
                });

                //Chỉnh sửa thông tin admin
                fmBinding.rlTenAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NavDirections action = ADM_QuanTriHeThongFMDirections.actionADMQuanTriHeThongFMToADMChinhSuaThongTinFM(DangNhapFM.TENTK);
                        navController.navigate(action);
                    }
                });

                //Đổi mật khẩu
                fmBinding.llDoiMatKhauAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NavDirections action = ADM_QuanTriHeThongFMDirections.actionADMQuanTriHeThongFMToADMDoiMatKhauFM();
                        navController.navigate(action);
                    }
                });

                //Đăng xuất
                fmBinding.llDangXuatAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
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
