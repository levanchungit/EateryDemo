package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.fragments.DangNhapFM.hideKeyboard;
import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.os.Bundle;
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

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.FragmentChinhsuaThongtinBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChinhSuaThongTinFM extends Fragment {
    FragmentChinhsuaThongtinBinding fmEditProfileBinding;
    NavController navController;
    View _view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmEditProfileBinding = FragmentChinhsuaThongtinBinding.inflate(getLayoutInflater());
        _view = container;

        return fmEditProfileBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClick();

        //tắt bottom navigation
        BottomNavigationView navbar = getActivity().findViewById(R.id.navBot);
        navbar.setVisibility(View.GONE);

        GetThongTin(DangNhapFM.TENTK);
        initNavController(_view);
    }



    private void initNavController(View viewEditProfileBinding) {
        navController = Navigation.findNavController(viewEditProfileBinding);
    }

    private void initClick() {


        fmEditProfileBinding.btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String _TenTK = DangNhapFM.TENTK;
                String _HoTen = fmEditProfileBinding.edtFullName.getText().toString().trim();
                String _SDT = fmEditProfileBinding.edtSDT.getText().toString().trim();
                String _DiaChi = fmEditProfileBinding.edtDiaChi.getText().toString().trim();
                ChinhSuaThongTin(_TenTK, _HoTen, _SDT, _DiaChi);
                dismissProgressDialog();
            }
        });
    }

    private void GetThongTin(String _TenTK) {
        showProgressDialog(getContext(), "Đang xử lý");
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetTaiKhoanTheoTenTK(_TenTK);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                TaiKhoan taikhoan = (TaiKhoan) response.body();
                fmEditProfileBinding.edtFullName.setText(taikhoan.getHoTen());
                fmEditProfileBinding.edtSDT.setText(taikhoan.getSDT());
                fmEditProfileBinding.tvEmail.setText(taikhoan.getTenTK());
                fmEditProfileBinding.edtDiaChi.setText(taikhoan.getDiaChi());
                Glide.with(getContext()).load(taikhoan.getHinhAnh()).centerCrop().into(fmEditProfileBinding.ivAvatarAdminChinhSuathongTin);
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ChinhSuaThongTin(String _TenTK, String _HoTen, String _SDT, String _DiaChi) {
        showProgressDialog(getContext(), "Đang xác nhân");
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.ChinhSuaThongTin(_TenTK, _HoTen, _SDT, _DiaChi);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                Toast.makeText(getActivity(), message.getNotification(), Toast.LENGTH_SHORT).show();
                if (message.getStatus() == 1) {
                    NavDirections action = ChinhSuaThongTinFMDirections.actionEditProfileFMToMenuThongTin();
                    Navigation.findNavController(getView()).navigate(action);
                    hideKeyboard(getActivity());
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