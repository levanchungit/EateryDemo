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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eaterydemo.adapter.AdminQuanLyCuaHangAdapter;
import com.example.eaterydemo.adapter.AdminQuanLyTaiKhoanAdapter;
import com.example.eaterydemo.adapter.NhaHangHCNAdapter;
import com.example.eaterydemo.databinding.FragmentAdminQuanlycuahangBinding;
import com.example.eaterydemo.databinding.FragmentAdminQuanlytaikhoanBinding;
import com.example.eaterydemo.databinding.FragmentNhahangBinding;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminQuanLyTaiKhoanFM extends Fragment {
    FragmentAdminQuanlytaikhoanBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentAdminQuanlytaikhoanBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        showProgressDialog(getContext(), "Đang tải dữ liệu");
        GetAllNhaHang();

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

    }

    private void GetAllNhaHang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetAllTaiKhoan();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<TaiKhoan> arr = (List<TaiKhoan>) response.body();
                Log.d("arr", arr.size() + "");
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                fmBinding.rvQuanLyTaiKhoan.setLayoutManager(linearLayoutManager);
                AdminQuanLyTaiKhoanAdapter adapter = new AdminQuanLyTaiKhoanAdapter(arr, getContext());
                fmBinding.rvQuanLyTaiKhoan.setAdapter(adapter);
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