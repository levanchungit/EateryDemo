package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.MonAnChiTietAdapter;
import com.example.eaterydemo.databinding.FragmentMonanchitietBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonAnChiTietFM extends Fragment {
    FragmentMonanchitietBinding fmBinding;
    NavController navController;
    MonAn monAn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentMonanchitietBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);



        GetMonAnChiTiet();
        GetAllMonAnNhaHangChiTiet();

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

        fmBinding.imgGiamSoLuongMACT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int SoLuongMon = Integer.parseInt(fmBinding.txtSoLuongChiTiet.getText().toString());
                int SL = SoLuongMon - 1;
                if (SL >= 1){
                    fmBinding.txtSoLuongChiTiet.setText(SL+"");
                }else{
                    fmBinding.txtSoLuongChiTiet.setText(0 + "");
                }

            }
        });

        fmBinding.imgTangSoLuongMACT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int SoLuongMon = Integer.parseInt(fmBinding.txtSoLuongChiTiet.getText().toString());
                int SL = SoLuongMon + 1;
                fmBinding.txtSoLuongChiTiet.setText(SL+"");
            }
        });

        fmBinding.btnThemMonAnMonAnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _TENTK = DangNhapFM.TENTK;
                int _MaMA = monAn.getMaMA();
                int _SL = Integer.parseInt(fmBinding.txtSoLuongChiTiet.getText().toString());
                ThemMonAnVaoGioHang(_TENTK, _MaMA, _SL);
            }
        });

    }

    private void GetMonAnChiTiet() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        int maMonAn = MonAnChiTietFMArgs.fromBundle(getArguments()).getMaMA();
        Call call = serviceAPI.GetMonAnTheoMaMA(maMonAn);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                monAn = (MonAn) response.body();
                int i = (int) monAn.getGia();
                fmBinding.txtTenMonAnChiTiet.setText(monAn.getTenMA());
                fmBinding.txtGiaMonAnChiTiet.setText(i + " đ");
                fmBinding.txtTenNhaHangChiTiet2.setText(monAn.getTenNH());
                Glide.with(getContext()).load(monAn.getHinhAnh()).centerCrop().placeholder(R.drawable.img_error).into(fmBinding.imgMonAnChiTiet);

                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetAllMonAnNhaHangChiTiet() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        int maNH = MonAnChiTietFMArgs.fromBundle(getArguments()).getMaNH();
        Call call = serviceAPI.GetAllMonAnTheoNhaHang(maNH);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<MonAn> arr = (List<MonAn>) response.body();
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                fmBinding.rvMonAnMonAnChiTiet.setLayoutManager(staggeredGridLayoutManager);
                MonAnChiTietAdapter adapter = new MonAnChiTietAdapter(arr, getContext());
                fmBinding.rvMonAnMonAnChiTiet.setAdapter(adapter);
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ThemMonAnVaoGioHang(String _TenTK, int _MaMA, int SL) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.ThemMonAnVaoGioHang(_TenTK, _MaMA, SL);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
//                Message message = (Message) response.body();
//                Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();

                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ThemMonAnKhacNhaHang(String _TenTK, int _MaMA) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.ThemMonAnKhacNhaHang(_TenTK, _MaMA);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
//                if()
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
