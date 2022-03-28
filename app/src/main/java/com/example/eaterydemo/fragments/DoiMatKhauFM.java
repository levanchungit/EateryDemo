package com.example.eaterydemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.eaterydemo.databinding.FragmentDoimatkhauBinding;


public class DoiMatKhauFM extends Fragment {
    FragmentDoimatkhauBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentDoimatkhauBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

    }

//    private void GetAllNhaHang() {
//        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
//        Call call = serviceAPI.GetAllNhaHang();
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                List<NhaHang> arr = (List<NhaHang>) response.body();
//                Log.d("arr", arr.size() + "");
//                NhaHangVuongAdapter adapter = new NhaHangVuongAdapter(arr, getContext());
//                fmBinding.rvKhao20ValentineTrang.setAdapter(adapter);
//                dismissProgressDialog();
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                dismissProgressDialog();
//                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
