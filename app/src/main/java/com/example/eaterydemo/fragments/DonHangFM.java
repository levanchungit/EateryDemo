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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.eaterydemo.adapter.DonHangAdapter;
import com.example.eaterydemo.databinding.FragmentDonhangBinding;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DonHangFM extends Fragment {
    FragmentDonhangBinding fmBinding;
    NavController navController;
    View _view;
    private  List<DonHang> arr;
    DonHangAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentDonhangBinding.inflate(getLayoutInflater());
        initNavController(container);
        _view = container;
        return fmBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController(_view);
        showProgressDialog(getContext(), "Đang tải dữ liệu");
        getAllDonHang();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }


        private void getAllDonHang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetALLDonHang("user1");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arr = (List<DonHang>) response.body();
                Log.d("arr", arr.size() + "");
                adapter = new DonHangAdapter(arr, getContext());
                fmBinding.rvDonHang.setAdapter(adapter);
                fmBinding.rvDonHang.setLayoutManager(new LinearLayoutManager(getContext()));
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
