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
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.eaterydemo.databinding.FragmentQuenmatkhauBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.service.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuenMatKhauFM extends Fragment {
    FragmentQuenmatkhauBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentQuenmatkhauBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        fmBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = QuenMatKhauFMDirections.actionQuenMatKhauFMToDangNhapFM();
                navController.navigate(action);
            }
        });

        fmBinding.btnGuiKiemTraQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1. nếu là email thì toast thông báo kêu ktra email để rs mk
                //2. Nếu là sdt là chuyển màn hình tiếp => nhập mã OTP => đổi mật khẩu
                String _email = fmBinding.edtEmailDangNhap.getText().toString();
                if (_email != null) {
                    quenMatKhau(_email);
                    DangNhapFM.TENTK = _email;
                }

            }
        });
    }

    private void quenMatKhau(String _TenTK) {
        showProgressDialog(getContext(),"Đang gửi yêu cầu...");
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.SendEmailForgotPassword(_TenTK);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                Toast.makeText(requireContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                Log.e("CHECK QuenMK",message.getNotification());
                if (message.getStatus() == 1) {
                    NavDirections action = QuenMatKhauFMDirections.actionQuenMatKhauFMToQuenMatKhauDoiMatKhauMoiFM();
                    navController.navigate(action);
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
