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
import com.example.eaterydemo.adapter.NhaHangHCNAdapter;
import com.example.eaterydemo.databinding.FragmentAdminQuanlynhahangBinding;
import com.example.eaterydemo.databinding.FragmentNhahangBinding;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ADM_QuanLyCuaHangFM extends Fragment {
    FragmentAdminQuanlynhahangBinding fmBinding;
    NavController navController;
    public static int MaNH;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentAdminQuanlynhahangBinding.inflate(getLayoutInflater());
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
        fmBinding.ivAddAdminQuanLyNhaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ADM_QuanLyCuaHangFMDirections.actionAdminQuanLyCuaHangFMToADMAddNhaHangFM();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private void GetAllNhaHang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetAllNhaHang();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<NhaHang> arr = (List<NhaHang>) response.body();
                Log.d("arr", arr.size() + "");
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                fmBinding.rvQuanLyCuaHang.setLayoutManager(linearLayoutManager);
                AdminQuanLyCuaHangAdapter adapter = new AdminQuanLyCuaHangAdapter(arr, getContext());
                fmBinding.rvQuanLyCuaHang.setAdapter(adapter);
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
