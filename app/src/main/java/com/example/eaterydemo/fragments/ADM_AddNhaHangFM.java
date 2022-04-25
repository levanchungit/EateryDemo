package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.fragments.DangKyFM.validateEditText;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.eaterydemo.databinding.FragmentAdminAddnhahangBinding;
import com.example.eaterydemo.model.LoaiNhaHang;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ADM_AddNhaHangFM extends Fragment {
    FragmentAdminAddnhahangBinding fmBinding;
    NavController navController;
    List<TaiKhoan> list;
    List<LoaiNhaHang> list2;
    View _view;
    String _tentk;
    String _loainh;
    private static int IMAGE_REQ = 1;
    int isValid = 0;
    private Uri imagePath;
    ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentAdminAddnhahangBinding.inflate(getLayoutInflater());
        _view = container;
        return fmBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GetTenTKSpinner();
        GetLoaiNHSpinner();
        initClick();
        initNavController(_view);
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        fmBinding.ivAvatarAddNhaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
            }
        });
        fmBinding.btnThemNHAddNhaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateEditText(fmBinding.tlTenNHAddNhaHang, fmBinding.edtTenNHAddNhaHang) |
                        !validateEditText(fmBinding.tlDiaChiAddNhaHang, fmBinding.edtDiaChiAddNhaHang) |
                        !validateEditText(fmBinding.tlMoTaAddNhaHang, fmBinding.edtMoTaAddNhaHang) ){
                    return;
                }

                uploadToCloudinary();
                dismissProgressDialog();
            }
        });
        fmBinding.spnChuNhaHangAddNhaHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("f", i + "");
                _tentk = list.get(i).getTenTK();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        fmBinding.spnLoaiNhaHangAddNhaHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("f", i + "");
                _loainh = list2.get(i).getMaLoaiNH();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    //Tên tk gán lên spinner
    private void GetTenTKSpinner(){
        Call call = serviceAPI.GetAllTaiKhoanChuaCoNhaHang();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                list = (List<TaiKhoan>) response.body();
                List<String> arrTenNguoiDung = new ArrayList<>();
                for (TaiKhoan tmp : list) {
                    arrTenNguoiDung.add(tmp.getTenTK());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, arrTenNguoiDung);
                fmBinding.spnChuNhaHangAddNhaHang.setAdapter(arrayAdapter);
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //Loại nhà hàng gán lên spinner
    private void GetLoaiNHSpinner(){
        Call call = serviceAPI.GetAllLoaiNhaHang();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                list2 = (List<LoaiNhaHang>) response.body();
                List<String> arrTenLoai = new ArrayList<>();
                for (LoaiNhaHang tmp : list2) {
                    arrTenLoai.add(tmp.getTenLoaiNH());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, arrTenLoai);
                fmBinding.spnLoaiNhaHangAddNhaHang.setAdapter(arrayAdapter);
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //Lấy hình ảnh
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
            Glide.with(getContext()).load(imagePath).into(fmBinding.ivAvatarAddNhaHang);
        }
    }

    private void AddNhaHang(NhaHang model) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.ThemNhaHang(model);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                if (message.getStatus() == 1) {
                    NavDirections action = ADM_AddNhaHangFMDirections.actionADMAddNhaHangFMToAdminQuanLyCuaHangFM();
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

    //Thêm nhà hàng
    private void uploadToCloudinary() {
        try{
            if(imagePath == null){
                Toast.makeText(getContext(), "Vui lòng chọn ảnh", Toast.LENGTH_SHORT).show();
                return;
            }

            MediaManager.get().cancelAllRequests();
            MediaManager.get().upload(imagePath).callback(new UploadCallback() {
                @Override
                public void onStart(String requestId) {
                    showProgressDialog(getContext(), "Đang đăng ký nhà hàng");
                    Log.d("CLOUDINARY", "Start");
                }

                @Override
                public void onProgress(String requestId, long bytes, long totalBytes) {

                }

                @Override
                public void onSuccess(String requestId, Map resultData) {
                    Log.d("CLOUDINARY", "Task successful");
                    String _tennh = fmBinding.edtTenNHAddNhaHang.getText().toString().trim();
                    String _diachi = fmBinding.edtDiaChiAddNhaHang.getText().toString().trim();
                    String _mota = fmBinding.edtMoTaAddNhaHang.getText().toString().trim();
                    String _hinhAnh = resultData.get("url").toString();
                    NhaHang nhaHang = new NhaHang(_tennh, _diachi, _hinhAnh, _mota, _tentk, _loainh);
                    AddNhaHang(nhaHang);
                    dismissProgressDialog();
                }

                @Override
                public void onError(String requestId, ErrorInfo error) {
                    Toast.makeText(getContext(), "Task Not successful " + error, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onReschedule(String requestId, ErrorInfo error) {

                }
            }).dispatch();
        } catch (Exception e){
            Log.e("Error: ", e + "");
        }
    }
}
