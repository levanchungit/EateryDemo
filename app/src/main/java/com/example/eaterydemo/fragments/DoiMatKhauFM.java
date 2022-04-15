package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.fragments.DangNhapFM.validateEditText;
import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.content.Intent;
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

import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.FragmentDoimatkhauBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.service.ServiceAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.eaterydemo.activities.DrawerLayoutActivity;
import com.example.eaterydemo.databinding.FragmentDoimatkhauBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoiMatKhauFM extends Fragment {
    FragmentDoimatkhauBinding fmBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentDoimatkhauBinding.inflate(getLayoutInflater());
        initClick();

        //tắt bottom navigation
        BottomNavigationView navbar = getActivity().findViewById(R.id.navBot);
        navbar.setVisibility(View.GONE);

        return fmBinding.getRoot();
    }

    private void initClick() {
        fmBinding.imgBackDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = DoiMatKhauFMDirections.actionDoiMatKhauFMToMenuThongTin();
                Navigation.findNavController(view).navigate(action);
            }
        });

        fmBinding.btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validate Input
                if (!validateEditText(fmBinding.tilNhapLaiMatKhauMoiDoiMatKhau, fmBinding.edtNhapLaiMatKhauMoiDoiMatKhau)
                        | !validateEditText(fmBinding.tilNhapMatKhauCuDoiMatKhau, fmBinding.edtNhapMatKhauCuDoiMatKhau)
                        | !validateEditText(fmBinding.tilNhapMatKhauMoiDoiMatKhau, fmBinding.edtNhapMatKhauMoiDoiMatKhau)) {
                    return;
                }

                String _TenTK = DangNhapFM.TENTK;
                String _MatKhauCu = fmBinding.edtNhapMatKhauCuDoiMatKhau.getText().toString().trim();
                String _MatKhauMoi = fmBinding.edtNhapMatKhauMoiDoiMatKhau.getText().toString().trim();
                String _NhapLaiMatKhauMoi = fmBinding.edtNhapLaiMatKhauMoiDoiMatKhau.getText().toString().trim();

                CapNhatMatKhauCuaTK(_TenTK, _MatKhauCu, _MatKhauMoi, _NhapLaiMatKhauMoi);


            }
        });
    }

    private void CapNhatMatKhauCuaTK(String TenTK, String MatKhauCu, String MatKhauMoi, String NhapLaiMatKhauMoi) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.CapNhatMatKhauCuaTK(TenTK, MatKhauCu, MatKhauMoi, NhapLaiMatKhauMoi);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                if(message.getStatus() == 1){
                    NavDirections action = DoiMatKhauFMDirections.actionDoiMatKhauFMToMenuThongTin();
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
