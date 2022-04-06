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
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.NhaHangHCNTimKiemAdapter;
import com.example.eaterydemo.databinding.FragmentTimkiemnhahangBinding;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.service.ServiceAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TimKiemNhaHangFM extends Fragment {
    FragmentTimkiemnhahangBinding fmBinding;
    NhaHangHCNTimKiemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentTimkiemnhahangBinding.inflate(getLayoutInflater());
        initClick();

        //tắt bottom navigation
        BottomNavigationView navbar = getActivity().findViewById(R.id.navBot);
        navbar.setVisibility(View.GONE);

        getAllNhaHang();
        fmBinding.svTimKiemTimKiemNhaHang.requestFocus();

        return fmBinding.getRoot();
    }

    private void initClick() {

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

        fmBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = TimKiemNhaHangFMDirections.actionTimKiemNhaHangFMToMenuTrangChu();
                Navigation.findNavController(view).navigate(action);
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
                adapter = new NhaHangHCNTimKiemAdapter(arr, getContext());
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
