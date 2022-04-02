package com.example.eaterydemo.fragments;


import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eaterydemo.adapter.NhaHangHCNAdapter;
import com.example.eaterydemo.databinding.FragmentTimkiemnhahangBinding;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TimKiemNhaHangFM extends Fragment {
    FragmentTimkiemnhahangBinding fmBinding;
    NavController navController;
    NhaHangHCNAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentTimkiemnhahangBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        getAllNhaHang();
        fmBinding.svTimKiemTimKiemNhaHang.requestFocus();

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        fmBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = TimKiemNhaHangFMDirections.actionTimKiemNhaHangFMToMenuTrangChu2();
                navController.navigate(action);
            }
        });

        fmBinding.svTimKiemTimKiemNhaHang.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void getAllNhaHang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetAllNhaHang();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<NhaHang> arr = (List<NhaHang>) response.body();
                adapter = new NhaHangHCNAdapter(arr, getContext());
                fmBinding.rvTimKiemNhaHangTrangChu.setAdapter(adapter);
                fmBinding.rvTimKiemNhaHangTrangChu.setLayoutManager(new LinearLayoutManager(getContext()));
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
