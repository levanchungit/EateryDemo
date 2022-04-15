package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.MaKhuyenMaiAdapter;
import com.example.eaterydemo.databinding.FragmentQuanlykhuyenmaiBinding;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CCH_MaKhuyenMaiFM extends Fragment {
    FragmentQuanlykhuyenmaiBinding fmBinding;
    NavController navController;
    View _view;
    private List<KhuyenMai> arr;
    //xulyhinhanh
    private static int IMAGE_REQ = 1;
    private Uri imagePath;

    EditText maKm, tenKm, TienKm, slKm;
    ImageView HinhAnhKm;
    Button btnChinhSua;
    //xu ly refr
    List<KhuyenMai> arrThem;
    MaKhuyenMaiAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentQuanlykhuyenmaiBinding.inflate(getLayoutInflater());
        _view = container;


        fmBinding.addMaKhuyenMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showdialog();
            }
        });
        return fmBinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showProgressDialog(getContext(), "Đang tải dữ liệu");
        GetAllMaKhuyenMaiTheoNH();
    }

    private void GetAllMaKhuyenMaiTheoNH() {
        //test
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetAllMaKhuyenMaiTheoNH(CCH_QuanLyNhaHangFM.MaNH);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arr = (List<KhuyenMai>) response.body();
                Log.d("arr", arr.size() + "");
                adapter = new MaKhuyenMaiAdapter(arr, getContext());
                fmBinding.rvViewKhuyenMai.setAdapter(adapter);
                fmBinding.rvViewKhuyenMai.setLayoutManager(new LinearLayoutManager(getContext()));
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showdialog() {

        AlertDialog.Builder b = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_themmakhuyenmai, null);
        b.setView(view);

        Dialog dialog = b.create();

        maKm = view.findViewById(R.id.txtThemMadialog);
        tenKm = view.findViewById(R.id.txtTenKhuyenMaidialog);
        TienKm = view.findViewById(R.id.txtTienKhuyenMaidialog);
        slKm = view.findViewById(R.id.txtSoLuongKhuyenMaidialog);
        HinhAnhKm = view.findViewById(R.id.imgMaKhuyen);
        btnChinhSua = view.findViewById(R.id.btnChinhSuadialog);

        HinhAnhKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
            }
        });

        dialog.show();
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadToCloudinary();
                dialog.dismiss();
            }
        });
    }


    private void ThemMaKhuyenMai(KhuyenMai khuyenMai) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.ThemMaKhuyenMai(khuyenMai);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrThem = (List<KhuyenMai>) response.body();
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
        arr.addAll(arrThem);
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
            Glide.with(getContext()).load(imagePath).into(HinhAnhKm);
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
                Toast.makeText(getContext(), "Task successful", Toast.LENGTH_SHORT).show();
                showProgressDialog(getContext(), "Đang đăng ký tài khoản");
                String MaKM = maKm.getText().toString().trim() ;
                String TenKhuyenMai = tenKm.getText().toString().trim();
                double TienKM = Double.parseDouble(TienKm.getText().toString().trim());
                int SL = Integer.parseInt(slKm.getText().toString().trim());
                int MaNH = CCH_QuanLyNhaHangFM.MaNH;
                String HinhAnh = resultData.get("url").toString();
                ThemMaKhuyenMai(new KhuyenMai(MaKM, TenKhuyenMai, (int) TienKM, SL, MaNH, HinhAnh));

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
