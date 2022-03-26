package com.example.eaterydemo.fragments;


import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.ServiceAPI.BASE_Service;

import android.content.Intent;
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

import com.example.eaterydemo.databinding.FragmentDangkyBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DangKyFM extends Fragment {
    FragmentDangkyBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentDangkyBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

        fmBinding.btnLoginDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog(getContext(), "Đang thêm sản phẩm");
                String _email = fmBinding.edtEmailDangKy.getText().toString().trim();
                String _mk = fmBinding.edtEmailDangKy.getText().toString().trim();
                String _hoten = fmBinding.edtEmailDangKy.getText().toString().trim();
                String _sdt = fmBinding.edtEmailDangKy.getText().toString().trim();
                String _diachi = fmBinding.edtEmailDangKy.getText().toString().trim();
                TaiKhoan taiKhoan = new TaiKhoan(_email,_mk,_hoten,_sdt,_diachi,"","user");
                addTaiKhoan(taiKhoan);
            }
        });

        fmBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = DangKyFMDirections.actionDangKyFMToDangNhapFM();
                navController.navigate(action);
            }
        });
    }

    private void addTaiKhoan(TaiKhoan product) {

        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.AddTaiKhoan(product)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(Message message) {
        dismissProgressDialog();
        try {
            Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
            if (message.getStatus() == 1) {
                NavDirections action = DangKyFMDirections.actionDangKyFMToDangNhapFM();
                navController.navigate(action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleError(Throwable error) {
        dismissProgressDialog();
        Toast.makeText(getContext(), "Lỗi add tài khoản", Toast.LENGTH_SHORT).show();
    }

}
