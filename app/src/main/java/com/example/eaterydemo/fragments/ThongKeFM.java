package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.DonHangAdapter;
import com.example.eaterydemo.adapter.ThongKeAdapter;
import com.example.eaterydemo.databinding.FragmentDonhangBinding;
import com.example.eaterydemo.databinding.FragmentThongkeBinding;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.ThongKe;
import com.example.eaterydemo.service.ServiceAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThongKeFM extends Fragment {
    FragmentThongkeBinding fmBinding;
    NavController navController;
    View _view;
    private List<ThongKe> arr;
    ThongKeAdapter adapter;
    EditText edtTuNgay, edtDenNgay;
    DecimalFormat df = new DecimalFormat("#,###");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentThongkeBinding.inflate(getLayoutInflater());
        //tắt bottom navigation
//        BottomNavigationView navbar = getActivity().findViewById(R.id.navBot);
//        navbar.setVisibility(View.GONE);

//        initClick();
        initNavController(container);
        _view = container;
        return fmBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController(_view);
        edtTuNgay = view.findViewById(R.id.edtTuNgay_ThongKe);
        edtDenNgay = view.findViewById(R.id.edtDenNgay_ThongKe);

        showProgressDialog(getContext(), "Đang tải dữ liệu");
        thongKeTongDoanhThuNhaHangTheoNgay();
        thongKeTongDoanhThuTungMonAn();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }


    private void thongKeTongDoanhThuNhaHangTheoNgay() {
        String tuNgay = edtTuNgay.getText().toString();
        String denNgay = edtDenNgay.getText().toString();
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetTongDoanhThuDonHangTheoNH(0, "2022-04-06", "2022-04-08");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Double TongTien = (Double) response.body();
                fmBinding.tvTongTienDoanhThuThongKe.setText(df.format(TongTien) + "đ");
                Log.d("Toong tien : ", TongTien + "d");
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void thongKeTongDoanhThuTungMonAn() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetTongDoanhThuCuaTungMonAnTheoNH(0);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arr = (List<ThongKe>) response.body();
                Log.d("arr", arr.size() + "");
                adapter = new ThongKeAdapter(arr, getContext());
                fmBinding.rvThongKe.setAdapter(adapter);
                fmBinding.rvThongKe.setLayoutManager(new LinearLayoutManager(getContext()));
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