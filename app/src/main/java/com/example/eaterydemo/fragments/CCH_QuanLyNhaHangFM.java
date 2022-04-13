package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.os.Bundle;
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
import com.example.eaterydemo.databinding.FragmentOwnerQuanlynhahangBinding;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.service.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CCH_QuanLyNhaHangFM extends Fragment {
    FragmentOwnerQuanlynhahangBinding fmBinding;
    NavController navController;
    View _view;
    public static int MaNH;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentOwnerQuanlynhahangBinding.inflate(getLayoutInflater());
        _view = container;
        return fmBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClick();
        initNavController(_view);

        showProgressDialog(getContext(), "Đang tải dữ liệu");
        getChuCuaHang();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        fmBinding.tvThongTinChuCuaHangCCHQuanLyNhaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = CCH_QuanLyNhaHangFMDirections.actionCCHQuanLyNhaHangFMToCCHChinhSuaThongTinNhaHangFM3();
                navController.navigate(action);
            }
        });

        fmBinding.ivQuanLyDanhSachMonAnCCHQuanLyNhaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = CCH_QuanLyNhaHangFMDirections.actionCCHQuanLyNhaHangFMToCCHItemQuanLyDanhSachMonAn();
                navController.navigate(action);
            }
        });



        fmBinding.tvThongTinChuCuaHangCCHQuanLyNhaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = CCH_QuanLyNhaHangFMDirections.actionCCHQuanLyNhaHangFMToCCHChinhSuaThongTinNhaHangFM3();
                navController.navigate(action);
            }
        });


        fmBinding.ivQuanLyMaKhuyenMaiCCHQuanLyNhaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = CCH_QuanLyNhaHangFMDirections.actionCCHQuanLyNhaHangFMToMaKhuyenMai();
                navController.navigate(action);
            }
        });


        fmBinding.ivLichSuDonHangCCHQuanLyNhaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = CCH_QuanLyNhaHangFMDirections.actionCCHQuanLyNhaHangFMToCCHLichSuDonHang();
                navController.navigate(action);
            }
        });

    }

    private void getChuCuaHang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetNhaHangTheoTenTK(DangNhapFM.TENTK);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                NhaHang nh = (NhaHang) response.body();
                MaNH = nh.getMaNH();
                Glide.with(requireContext()).load(nh.getHinhAnh()).centerCrop().into(fmBinding.ivImageCCHQuanLyNhaHang);
                fmBinding.tvTenNhaHangCCHQuanLyNhaHang.setText(nh.getTenNH());
                fmBinding.tvDiaChiCCHQuanLyNhaHang.setText(nh.getDiaChi());
                fmBinding.tvTenChuCuaHangCCHQuanLyNhaHang.setText(nh.getHoTen());
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
