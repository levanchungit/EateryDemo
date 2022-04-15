package com.example.eaterydemo.fragments;


import static com.example.eaterydemo.fragments.DangKyFM.validateEditText;
import static com.example.eaterydemo.fragments.DangKyFM.validateEditTextEmail;
import static com.example.eaterydemo.fragments.DangKyFM.validateEditTextMK;
import static com.example.eaterydemo.fragments.DangKyFM.validateEditTextSDT;
import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.eaterydemo.databinding.FragmentDangkyBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ADM_AddTaiKhoanFM extends Fragment {
    FragmentDangkyBinding fmBinding;
    NavController navController;
    private static int IMAGE_REQ = 1;
    int isValid = 0;
    //cái này là gì đây?
    private Uri imagePath;


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

        fmBinding.imgImageDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
            }
        });

        fmBinding.btnLoginDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                //load hình ảnh lên cloudinary
                if(isValid == 1){
                    showProgressDialog(getContext(), "Đang tải dữ liệu");
                    uploadToCloudinary();
                    dismissProgressDialog();

                }
            }
        });

        fmBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ADM_AddTaiKhoanFMDirections.actionADMAddTaiKhoanFMToAdminQuanLyTaiKhoanFM();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private void requestPermission() {
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            selectImage();
        }else{
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },IMAGE_REQ);
        }
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_REQ && resultCode == Activity.RESULT_OK &&  data != null && data.getData() != null){
            imagePath = data.getData();
            Glide.with(getContext()).load(imagePath).into(fmBinding.imgImageDangKy);
        }
    }

    private void AddTaiKhoan(TaiKhoan model) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.DangKy(model);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                if (message.getStatus() == 1) {
                    NavDirections action = ADM_AddTaiKhoanFMDirections.actionADMAddTaiKhoanFMToAdminQuanLyTaiKhoanFM();
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

    @Override
    public void onStop() {
        super.onStop();

    }



    private void uploadToCloudinary() {
        try{
            MediaManager.get().upload(imagePath).callback(new UploadCallback() {
                @Override
                public void onStart(String requestId) {
                    Log.d("CLOUDINARY","Start");
                }

                @Override
                public void onProgress(String requestId, long bytes, long totalBytes) {

                }

                @Override
                public void onSuccess(String requestId, Map resultData) {
                    Log.d("CLOUDINARY","Task successful");
//                showProgressDialog(getContext(), "Đang đăng ký tài khoản");
                    String _email = fmBinding.edtEmailDangKy.getText().toString().trim();
                    String _mk = fmBinding.edtMatKhauDangKy.getText().toString().trim();
                    String _hoten = fmBinding.edtHoTenDangKy.getText().toString().trim();
                    String _sdt = fmBinding.edtSdtDangKy.getText().toString().trim();
                    String _diachi = fmBinding.edtDiaChiDangKy.getText().toString().trim();
                    String _hinhAnh = resultData.get("url").toString();
                    TaiKhoan taiKhoan = new TaiKhoan(_email, _mk, _hoten, _sdt, _diachi, _hinhAnh, "user");
                    AddTaiKhoan(taiKhoan);
                }

                @Override
                public void onError(String requestId, ErrorInfo error) {
                    Toast.makeText(getContext(), "Task Not successful " + error, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onReschedule(String requestId, ErrorInfo error) {

                }
            }).dispatch();
        }catch (Exception e){
            Log.e("Error: ",e + "");
        }
    }

    private void validate(){
        if (!validateEditText(fmBinding.tilEmailDangKy, fmBinding.edtEmailDangKy) |
                !validateEditText(fmBinding.tilHoTenDangKy, fmBinding.edtHoTenDangKy) |
                !validateEditText(fmBinding.tilMatKhauDangKy, fmBinding.edtMatKhauDangKy) |
                !validateEditText(fmBinding.tilDiaChiDangKy, fmBinding.edtDiaChiDangKy) |
                !validateEditText(fmBinding.tilHoTenDangKy, fmBinding.edtHoTenDangKy) |
                !validateEditText(fmBinding.tilSdtDangKy, fmBinding.edtSdtDangKy) ){
            return;
        } else if(!validateEditTextMK(fmBinding.tilMatKhauDangKy, fmBinding.edtMatKhauDangKy, fmBinding.tilNhapLaiMatKhauDangKy, fmBinding.edtNhapLaiMatKhauDangKy) |
                !validateEditTextEmail(fmBinding.tilEmailDangKy, fmBinding.edtEmailDangKy) |
                !validateEditTextSDT(fmBinding.tilSdtDangKy, fmBinding.edtSdtDangKy)){
            return;
        } else if (imagePath==null) {
            Toast.makeText(getContext(), "Vui lòng chọn ảnh", Toast.LENGTH_SHORT).show();
        } else {
            isValid = 1;
        }
    }
}
