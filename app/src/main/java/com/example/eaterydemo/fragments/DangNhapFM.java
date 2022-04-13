package com.example.eaterydemo.fragments;


import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.eaterydemo.activities.AdminActivity;
import com.example.eaterydemo.activities.ChuCuaHangActivity;
import com.example.eaterydemo.activities.DrawerLayoutActivity;
import com.example.eaterydemo.databinding.FragmentDangnhapBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.service.ServiceAPI;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapFM extends Fragment {
    FragmentDangnhapBinding fmBinding;
    NavController navController;
    public static String TENTK;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentDangnhapBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        fmBinding.edtEmailDangNhap.setText("user2@gmail.com");
        fmBinding.edtMatKhauDangNhap.setText("222");

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

        fmBinding.btnLoginDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validate Input
                if (!validateEditText(fmBinding.tilEmailDangNhap, fmBinding.edtEmailDangNhap) | !validateEditText(fmBinding.tilMatKhauDangNhap, fmBinding.edtMatKhauDangNhap)) {
                    return;
                }

                String _TenTK = fmBinding.edtEmailDangNhap.getText().toString().trim();
                String _MatKhau = fmBinding.edtMatKhauDangNhap.getText().toString().trim();
                DangNhap(_TenTK, _MatKhau);
            }
        });

        fmBinding.tvDangKyDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = DangNhapFMDirections.actionDangNhapFMToDangKyFM();
                navController.navigate(action);
            }
        });

        fmBinding.tvQuenMatKhauDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = DangNhapFMDirections.actionDangNhapFMToQuenMatKhauFM();
                navController.navigate(action);
            }
        });
    }

    private void DangNhap(String _TenTK, String _MatKhau) {
        showProgressDialog(getContext(), "Đang đăng nhập...");
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.DangNhap(_TenTK, _MatKhau);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                if (message != null) {
                    Toast.makeText(requireContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                    Log.e("LOGIN", message.getNotification());
                    dismissProgressDialog();
                    //11: Quản trị viên, 12: Chủ cửa hàng, 13: khách hàng
                    if (message.getStatus() == 11) {
                        startActivity(new Intent(requireContext(), AdminActivity.class));
                    } else if (message.getStatus() == 12) {
                        startActivity(new Intent(requireContext(), ChuCuaHangActivity.class));
                    } else if (message.getStatus() == 13) {
                        startActivity(new Intent(requireContext(), DrawerLayoutActivity.class));
                    }
                    TENTK = _TenTK;
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static boolean validateEditText(TextInputLayout til, TextInputEditText edt) {
        String _str = edt.getText().toString().trim();
        if (_str.isEmpty()) {
            til.setError("Vui lòng không bỏ trống");
            return false;
        } else {
            til.setError("");
            til.setErrorEnabled(false);
            return true;
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
