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
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.databinding.FragmentChinhsuaQuanlynhahangBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.service.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ADM_ChinhSuaThongTinNhaHangFM extends Fragment {
    FragmentChinhsuaQuanlynhahangBinding fmBinding;
    View _view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentChinhsuaQuanlynhahangBinding.inflate(getLayoutInflater());

        fmBinding.edtTenTKChuNhaHang.setEnabled(false);

        _view = container;
        return fmBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GetThongTinNhaHang();
        initClick();

        showProgressDialog(getContext(), "Đang tải dữ liệu");

    }
    private void initClick() {
        fmBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ADM_ChinhSuaThongTinNhaHangFMDirections.actionADMChinhSuaThongTinNhaHangFMToAdminQuanLyCuaHangFM();
                Navigation.findNavController(getView()).navigate(action);
            }
        });
        fmBinding.btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int _MaNH = ADM_QuanLyCuaHangFM.MaNH;
                String _TenNH = fmBinding.edtTenNhaHang.getText().toString().trim();
                String _DiaChi = fmBinding.edtDiaChiEditQLNhaHang.getText().toString().trim();
                String _MoTa = fmBinding.edtMoTaEditQLNhaHang.getText().toString().trim();
                ChinhSuaThongTinNhaHang(new NhaHang(_MaNH,_TenNH,_DiaChi,_MoTa));
            }
        });
    }


    private void GetThongTinNhaHang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        int maNh = ADM_ChinhSuaThongTinNhaHangFMArgs.fromBundle(getArguments()).getMaNh();
        Call call = serviceAPI.GetNhaHangTheoMaNH(maNh);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                NhaHang nh = (NhaHang) response.body();
                Glide.with(requireContext()).load(nh.getHinhAnh()).centerCrop().into(fmBinding.ivAvatarEditQLNhaHang);
                fmBinding.edtTenNhaHang.setText(nh.getTenNH());
                fmBinding.edtDiaChiEditQLNhaHang.setText(nh.getDiaChi());
                fmBinding.edtTenTKChuNhaHang.setText(nh.getHoTen());
                fmBinding.edtMoTaEditQLNhaHang.setText(nh.getMoTa());
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ChinhSuaThongTinNhaHang(NhaHang nhaHang) {
        showProgressDialog(getContext(),"Đang xác nhân");
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.ChinhSuaThongTinNhaHang(nhaHang);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                Log.e("LOGIN",message.getNotification());
                if (message.getStatus() == 1) {
                    NavDirections action = ADM_ChinhSuaThongTinNhaHangFMDirections.actionADMChinhSuaThongTinNhaHangFMToAdminQuanLyCuaHangFM();
                    Navigation.findNavController(getView()).navigate(action);
                }
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