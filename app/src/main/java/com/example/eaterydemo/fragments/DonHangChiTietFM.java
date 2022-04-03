package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.DonHangChiTietAdapter;
import com.example.eaterydemo.databinding.FragmentChitietdonhangNguoidungBinding;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.DonHangChiTiet;
import com.example.eaterydemo.model.TaiKhoan;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class DonHangChiTietFM extends Fragment {
    FragmentChitietdonhangNguoidungBinding fmBinding;
    NavController navController;
    private List<DonHangChiTiet> arrDHCT = new ArrayList<>();
    TaiKhoan taiKhoan;
    DonHangChiTietAdapter adapter;
    DonHangChiTiet donHangChiTiet;
    View _view;
    TextView tvTenNhaHang_DHChiTiet, tvNgayMuaDHChiTiet, tvTongTien_DHChiTiet, tvMaDH_DHChiTiet, tvTenKH_DHChiTiet, tvSDT_DHChiTiet, tvDiaChi_DHChiTiet;
    DecimalFormat df = new DecimalFormat("#,###");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentChitietdonhangNguoidungBinding.inflate(getLayoutInflater());
        initNavController(container);
        _view = container;


        return fmBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTenNhaHang_DHChiTiet = view.findViewById(R.id.tvTenNhaHang_DHChiTiet);
        tvNgayMuaDHChiTiet = view.findViewById(R.id.tvNgayMuaDHChiTiet);
        tvTongTien_DHChiTiet = view.findViewById(R.id.tvTongTien_DHChiTiet);
        tvMaDH_DHChiTiet = view.findViewById(R.id.tvMaDH_DHChiTiet);
        tvTenKH_DHChiTiet = view.findViewById(R.id.tvTenKH_DHChiTiet);
        tvSDT_DHChiTiet = view.findViewById(R.id.tvSDT_DHChiTiet);
        tvDiaChi_DHChiTiet = view.findViewById(R.id.tvDiaChi_DHChiTiet);

        recieverDataDonHang();



    }

    private void recieverDataDonHang() {
        donHangChiTiet = new DonHangChiTiet();
        taiKhoan = new TaiKhoan();
        DonHang donHang = DonHangChiTietFMArgs.fromBundle(getArguments()).getDonhangchitiet();
        arrDHCT = donHang.getDONHANGCHITIETs();
        taiKhoan = donHang.getTAIKHOAN();
        tvTenNhaHang_DHChiTiet.setText(donHang.getNameRes());
        tvNgayMuaDHChiTiet.setText(donHang.getNgayMua());
        tvTongTien_DHChiTiet.setText(df.format(donHang.getTongTien())+"đ");
        tvMaDH_DHChiTiet.setText(donHang.getMaDonHang()+"");
        tvTenKH_DHChiTiet.setText(taiKhoan.getHoTen());
        tvDiaChi_DHChiTiet.setText(donHang.getDiaChi());
        tvSDT_DHChiTiet.setText(taiKhoan.getSDT()+"");
        Log.d("ten tai khoan", taiKhoan.getHoTen() + "");

        adapter = new DonHangChiTietAdapter(arrDHCT, getContext());
        fmBinding.rvMonAnDonHangChiTiet.setAdapter(adapter);
        fmBinding.rvMonAnDonHangChiTiet.setLayoutManager(new LinearLayoutManager(getContext()));
        dismissProgressDialog();

    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }


}
