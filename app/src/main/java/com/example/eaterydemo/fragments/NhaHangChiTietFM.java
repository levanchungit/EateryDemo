package com.example.eaterydemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.eaterydemo.databinding.FragmentNhahangchitietBinding;


public class NhaHangChiTietFM extends Fragment {
    FragmentNhahangchitietBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentNhahangchitietBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

//        showProgressDialog(getContext(), "Đang tải dữ liệu");

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        fmBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavDirections action = NhaHangFMDirections.actionNhaHangFMToHomeFM();
//                navController.navigate(action);

            }
        });
    }

//    private void GetAllMonAnTheoNhaHang() {
//        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
//        Call call = serviceAPI.GetAllNhaHangTheoLoai("LAU");
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                List<MonAn> arr = (List<MonAn>) response.body();
//                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
//                fmBinding.rvNhaHang.setLayoutManager(mLayoutManager);
//                NhaHangHCNAdapter adapter = new NhaHangHCNAdapter(arr, getContext());
//                fmBinding.rvNhaHang.setAdapter(adapter);
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
