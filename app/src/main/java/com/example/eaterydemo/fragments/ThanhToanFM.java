package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.GioHangAdapter;
import com.example.eaterydemo.adapter.MonAnAdapter;
import com.example.eaterydemo.adapter.NhaHangHCNAdapter;
import com.example.eaterydemo.databinding.FragmentThanhtoanBinding;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.DonHangChiTiet;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThanhToanFM extends Fragment {
    FragmentThanhtoanBinding fmBinding;
    NavController navController;
    List<DonHangChiTiet> arr = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentThanhtoanBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);
        GetDiaChi();
        GetMonAnTuDonHang();
        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        //Thay đổi địa chỉ
        fmBinding.txtThayDoiThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Thanh toán
        fmBinding.imgThanhToanThanToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void GetDiaChi() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetDonHangTheoTK("user1");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                DonHang donHang = (DonHang) response.body();
                int i = (int) donHang.getTongTien();
                fmBinding.txtDiaChiThanhToan.setText(donHang.getDiaChi());
                fmBinding.txtTongTienThanhToan.setText(i + " đ");
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetMonAnTuDonHang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetDonHangTheoTK("user1");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                DonHang model = (DonHang) response.body();
                for(int i=0;i<model.getDonHangChiTiet().size();i++){
                    //model.getDonHangChiTiet.get(i): là 1 object rồi nên dùng arr add bth vào chứ k phải cast
                    arr.add(model.getDonHangChiTiet().get(i));
                }
                Log.d("arr", arr.size() + "");
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                fmBinding.rvDonHangThanhToan.setLayoutManager(linearLayoutManager);
                GioHangAdapter adapter = new GioHangAdapter(arr, getContext());
                fmBinding.rvDonHangThanhToan.setAdapter(adapter);
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
