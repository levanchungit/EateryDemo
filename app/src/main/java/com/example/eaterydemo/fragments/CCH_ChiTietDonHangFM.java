package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.DonHangAdapter;
import com.example.eaterydemo.adapter.DonHangChiTietAdapter;
import com.example.eaterydemo.databinding.FragmentChitietdonhangNguoidungBinding;
import com.example.eaterydemo.databinding.FragmentOwnerChitietdonhangBinding;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.DonHangChiTiet;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CCH_ChiTietDonHangFM extends Fragment {
    FragmentOwnerChitietdonhangBinding fmBinding;
    NavController navController;
    private List<DonHangChiTiet> arrDHCT = new ArrayList<>();
    private List<DonHang> arrDH = new ArrayList<>();
    DonHangChiTietAdapter adapter;
    DonHangChiTiet donHangChiTiet;
    View _view;
    TextView tvMaDH_DHChiTiet, tvNgayMuaDHChiTiet, tvTongTien_DHChiTiet, tvTrangThai_DHChiTiet, tvTrangThaiThanhToan_DHChiTiet;
    ImageView ivTuChoiDH, ivXacNhanDH;
    DecimalFormat df = new DecimalFormat("#,###");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentOwnerChitietdonhangBinding.inflate(getLayoutInflater());
        initNavController(container);
        _view = container;


        return fmBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvMaDH_DHChiTiet = view.findViewById(R.id.tvMaDH_DHChiTiet_CCH);
        tvNgayMuaDHChiTiet = view.findViewById(R.id.tvNgayDH_DHChiTiet_CCH);
        tvTongTien_DHChiTiet = view.findViewById(R.id.tvTongTien_DHChiTiet_CCH);
        tvTrangThai_DHChiTiet = view.findViewById(R.id.tvTrangThaiChiTietDH_CCH);
        tvTrangThaiThanhToan_DHChiTiet = view.findViewById(R.id.tvTrangThaiThanhToanCTDH_CCH);
        ivTuChoiDH = view.findViewById(R.id.iv_TuChoiDH_CCH);
        ivXacNhanDH = view.findViewById(R.id.iv_XacNhanDH_CCH);

        recieverDataDonHang();
        UpdateTrangThaiDH();
    }

    private void recieverDataDonHang() {
        donHangChiTiet = new DonHangChiTiet();
        DonHang donHang = CCH_ChiTietDonHangFMArgs.fromBundle(getArguments()).getChiTietDonHangCCH();
        arrDHCT = donHang.getDONHANGCHITIETs();
        tvMaDH_DHChiTiet.setText("#000" + donHang.getMaDonHang());
        String ngay = donHang.getNgayMua();
        tvNgayMuaDHChiTiet.setText("Ngày nhận : "+(ngay.substring(0, 10)));
        tvTongTien_DHChiTiet.setText(df.format(donHang.getTongTien()) + "đ");
        adapter = new DonHangChiTietAdapter(arrDHCT, getContext());
        fmBinding.rvMonAnChiTietDHCCH.setAdapter(adapter);
        fmBinding.rvMonAnChiTietDHCCH.setLayoutManager(new LinearLayoutManager(getContext()));
        if (donHang.getTrangThaiDH() == 3) {
            tvTrangThai_DHChiTiet.setText("Chờ xác nhận");
            tvTrangThaiThanhToan_DHChiTiet.setText("*Chưa thanh toán");
            tvTrangThaiThanhToan_DHChiTiet.setTextColor(Color.RED);
            adapter = new DonHangChiTietAdapter(arrDHCT, getContext());
            fmBinding.rvMonAnChiTietDHCCH.setAdapter(adapter);
            fmBinding.rvMonAnChiTietDHCCH.setLayoutManager(new LinearLayoutManager(getContext()));
            dismissProgressDialog();
        } else if (donHang.getTrangThaiDH() == 2) {
            tvTrangThai_DHChiTiet.setText("Đã giao hàng");
            tvTrangThai_DHChiTiet.setTextColor(Color.GREEN);
            tvTrangThaiThanhToan_DHChiTiet.setText("*Đã thanh toán");
            tvTrangThaiThanhToan_DHChiTiet.setTextColor(Color.GREEN);
            ivTuChoiDH.setVisibility(View.INVISIBLE);
            ivXacNhanDH.setVisibility(View.INVISIBLE);
        }
    }

    private void UpdateTrangThaiDH() {
        ivTuChoiDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DonHang donHang = CCH_ChiTietDonHangFMArgs.fromBundle(getArguments()).getChiTietDonHangCCH();
                ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
                Call call = serviceAPI.CapNhatTrangThaiDonHang(donHang.getMaDonHang(), 3);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        NavDirections action = CCH_ChiTietDonHangFMDirections.actionCCHChiTietDonHangFMToCCHQuanLyDonHangFM2();
                        navController.navigate(action);
                        dismissProgressDialog();
                        Toast.makeText(getContext(), "Đã từ chối đơn hàng", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        dismissProgressDialog();
                        Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        ivXacNhanDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DonHang donHang = CCH_ChiTietDonHangFMArgs.fromBundle(getArguments()).getChiTietDonHangCCH();
                ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
                Call call = serviceAPI.CapNhatTrangThaiDonHang(donHang.getMaDonHang(), 2);

                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        NavDirections action = CCH_ChiTietDonHangFMDirections.actionCCHChiTietDonHangFMToCCHQuanLyDonHangFM2();
                        navController.navigate(action);
                        dismissProgressDialog();
                        Toast.makeText(getContext(), "Đã xác nhận đơn hàng", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call call, Throwable t) {
                        dismissProgressDialog();
                        Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }


}
