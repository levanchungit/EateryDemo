package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.CCH_ItemDangLam_QuanLyDonHangAdapter;
import com.example.eaterydemo.adapter.CCH_Item_QuanLyDanhSachMonAnAdapter;

import com.example.eaterydemo.adapter.MaKhuyenMaiAdapter;
import com.example.eaterydemo.databinding.FragmentQuanlydanhsachmonanBinding;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CCH_ItemQuanLyDanhSachMonAn extends Fragment {
    FragmentQuanlydanhsachmonanBinding fmBinding;
    NavController navController;
    View _view;
    private List<MonAn> arr;

    private static int IMAGE_REQ = 1;
    private Uri imagePath;


    CCH_Item_QuanLyDanhSachMonAnAdapter adapter;

    List<MonAn> arrRong;


    EditText tenMa, giaMa;
    ImageView hinhanhMa;
    Button btnThem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentQuanlydanhsachmonanBinding.inflate(getLayoutInflater());
        _view = container;

        fmBinding.faddThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLogThemMonAn();
            }
        });
        return fmBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showProgressDialog(getContext(), "Đang tải dữ liệu");
        GetAllMonAnTheoNhaHang();
    }

    private void GetAllMonAnTheoNhaHang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetAllMonAnTheoNhaHang(CCH_QuanLyNhaHangFM.MaNH);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arr = (List<MonAn>) response.body();
                Log.d("arr", arr.size() + "");
                adapter = new CCH_Item_QuanLyDanhSachMonAnAdapter(arr, getContext());
                fmBinding.rvQuanLyDanhSachMonAn.setAdapter(adapter);
                fmBinding.rvQuanLyDanhSachMonAn.setLayoutManager(new LinearLayoutManager(getContext()));
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void DiaLogThemMonAn() {
        AlertDialog.Builder b = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dailog_themmonan  ,null);
        b.setView(view);
        Dialog dialog = b.create();


        tenMa = view.findViewById(R.id.txtTenMon_ThemMonAn);
        giaMa = view.findViewById(R.id.txtPrice_ThemMonAn);
        hinhanhMa = view.findViewById(R.id.img_ThemMonAn);
        btnThem = view.findViewById(R.id.btn_ThemMonAn);

        hinhanhMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestPermission();
            }
        });
        dialog.show();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadToCloudinary();
                dialog.dismiss();
            }
        });
    }
    private void ThemMonAn(MonAn monAn) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.ThemMonAnTrongNhaHang(monAn);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrRong = (List<MonAn>) response.body();
                refreshRecycler();
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Log.e("erroo", t.toString());
            }
        });
    }

    private void refreshRecycler(){
        arr.clear();
        arr.addAll(arrRong);
        adapter.notifyDataSetChanged();
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
            Glide.with(getContext()).load(imagePath).into(hinhanhMa);
        }
    }
    private void uploadToCloudinary() {
        if (imagePath == null) {
            Toast.makeText(getContext(), "Vui long them hinh anh", Toast.LENGTH_SHORT).show();
            return;
        }
        MediaManager.get().upload(imagePath).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
                Toast.makeText(getContext(), "Start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
//                Toast.makeText(getContext(), "Task successful", Toast.LENGTH_SHORT).show();
                showProgressDialog(getContext(), "Đang đăng ký tài khoản");
                String TenMA = tenMa.getText().toString().trim() ;
                double GiaMA = Double.parseDouble(giaMa.getText().toString().trim());
                int MaNH = CCH_QuanLyNhaHangFM.MaNH;
                String HinhAnh = resultData.get("url").toString();
                ThemMonAn(new MonAn(TenMA, GiaMA,HinhAnh,MaNH));
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
    }

}
