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

import com.example.eaterydemo.databinding.FragmentQuenmatkhauotpBinding;

public class QuenMatKhau_OTPFM extends Fragment {
    FragmentQuenmatkhauotpBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentQuenmatkhauotpBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        fmBinding.btnTiepTucQuenMatKhauOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String _TenTK = DangNhapFM.TENTK;
//                String n1 = fmBinding.edtSoThu1.getText().toString().trim();
//                String n2 = fmBinding.edtSoThu2.getText().toString().trim();
//                String n3 = fmBinding.edtSoThu3.getText().toString().trim();
//                String n4 = fmBinding.edtSoThu4.getText().toString().trim();
//                String n5 = fmBinding.edtSoThu5.getText().toString().trim();
//                String n6 = fmBinding.edtSoThu6.getText().toString().trim();
//                String code = n1 + n2 + n3 + n4 + n5 + n6;
//                if (n1 != null && n2 != null && n3 != null && n4 != null && n5 != null){
//                    quenMatKhau(_TenTK, code, );
//                }
//                NavDirections action = QuenMatKhau_OTPFMDirections.actionQuenMatKhauOTPFMToQuenMatKhauDoiMatKhauMoiFM();
//                navController.navigate(action);

            }
        });
    }

//    private void quenMatKhau(String _TenTK, String _code, String _matKhauMoi) {
//        showProgressDialog(getContext(), "Đang xác nhận...");
//        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
//        Call call = serviceAPI.CapNhatMatKhau(_TenTK, _code, _matKhauMoi);
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                Message message = (Message) response.body();
//                Toast.makeText(requireContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
//                Log.e("CHECK QuenMK", message.getNotification());
//                if (message.getStatus() == 1) {
//                    NavDirections action = QuenMatKhauFMDirections.actionQuenMatKhauFMToQuenMatKhauOTPFM();
//                    navController.navigate(action);
//                }
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
