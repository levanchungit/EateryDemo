package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.os.Bundle;
import android.os.Handler;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.KhuyenMaiAdapter;
import com.example.eaterydemo.adapter.NhaHangHCNAdapter;
import com.example.eaterydemo.adapter.NhaHangVuongAdapter;
import com.example.eaterydemo.adapter.SlideAdapter;
import com.example.eaterydemo.databinding.FragmentTrangchuBinding;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TrangChuFM extends Fragment {
    NavController navController;
    FragmentTrangchuBinding fmBinding;
    int slideShow[] = {R.drawable.khuyenmai1, R.drawable.khuyenmai2, R.drawable.khuyenmai3, R.drawable.khuyenmai4};
    int currentPageCunter = 0;
    View _view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentTrangchuBinding.inflate(getLayoutInflater());
        _view = container;
        return fmBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClick();
        initNavController(_view);

        showProgressDialog(getContext(), "Đang tải dữ liệu");
        Khao20ValentineTrang();
        getAllKhuyenMai();
        getAllNhaHang();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        //SLIDESHOW KHUYẾN MÃI
        slideShowKhuyenMai(fmBinding.vpKhuyenMai1, slideShow);
        slideShowKhuyenMai(fmBinding.vpKhuyenMai2, slideShow);

        fmBinding.ivLau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loaiNh = "LAU";
                NavDirections action = TrangChuFMDirections.actionHomeFMToNhaHangFM(loaiNh);
                navController.navigate(action);
            }
        });
        fmBinding.ivCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loaiNh = "COM";
                NavDirections action = TrangChuFMDirections.actionHomeFMToNhaHangFM(loaiNh);
                navController.navigate(action);
            }
        });
        fmBinding.ivBun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loaiNh = "BunPho";
                NavDirections action = TrangChuFMDirections.actionHomeFMToNhaHangFM(loaiNh);
                navController.navigate(action);
            }
        });

        fmBinding.ivanvat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loaiNh = "AnVat";
                NavDirections action = TrangChuFMDirections.actionHomeFMToNhaHangFM(loaiNh);
                navController.navigate(action);
            }
        });
        fmBinding.ivdacsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loaiNh = "DacSan";
                NavDirections action = TrangChuFMDirections.actionHomeFMToNhaHangFM(loaiNh);
                navController.navigate(action);
            }
        });
        fmBinding.ivthucannhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loaiNh = "ThucAnNhanh";
                NavDirections action = TrangChuFMDirections.actionHomeFMToNhaHangFM(loaiNh);
                navController.navigate(action);
            }
        });
        fmBinding.ivdinhduong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loaiNh = "DinhDuong";
                NavDirections action = TrangChuFMDirections.actionHomeFMToNhaHangFM(loaiNh);
                navController.navigate(action);
            }
        });

        fmBinding.ivdacsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loaiNh = "DACSAN";
                NavDirections action = TrangChuFMDirections.actionHomeFMToNhaHangFM(loaiNh);
                navController.navigate(action);
            }
        });

        fmBinding.ivthucannhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loaiNh = "THUCANNHANH";
                NavDirections action = TrangChuFMDirections.actionHomeFMToNhaHangFM(loaiNh);
                navController.navigate(action);
            }
        });

        fmBinding.ivdinhduong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loaiNh = "DINHDUONG";
                NavDirections action = TrangChuFMDirections.actionHomeFMToNhaHangFM(loaiNh);
                navController.navigate(action);
            }
        });


        fmBinding.ivTimKiemNhaHangTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = TrangChuFMDirections.actionMenuTrangChuToTimKiemNhaHangFM();
                navController.navigate(action);
            }
        });

    }

    private void slideShowKhuyenMai(ViewPager vp, int[] arr) {
        //SLIDER KHUYENMAI 1
        vp.setAdapter(new SlideAdapter(arr, getContext()));
        //auto slide
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {

                if (currentPageCunter == arr.length) {
                    currentPageCunter = 0;
                }
                vp.setCurrentItem(currentPageCunter++, true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 5000, 3000);
    }

//    private void chonNhaHang()

    private void Khao20ValentineTrang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetAllNhaHangTheoLoai("ThucUong");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<NhaHang> arr = (List<NhaHang>) response.body();
                Log.d("arr", arr.size() + "");
                NhaHangVuongAdapter adapter = new NhaHangVuongAdapter(arr, getContext());
                fmBinding.rvKhao20ValentineTrang.setAdapter(adapter);
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi nhà hàng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllKhuyenMai() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetAllKhuyenMai();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<KhuyenMai> arr = (List<KhuyenMai>) response.body();
                Log.d("arr", arr.size() + "");
                KhuyenMaiAdapter adapter = new KhuyenMaiAdapter(arr, getContext());
                fmBinding.rvMonAnKemNuocSaiGon.setAdapter(adapter);
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllNhaHang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetAllNhaHang();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<NhaHang> arr = (List<NhaHang>) response.body();
                NhaHangHCNAdapter adapter = new NhaHangHCNAdapter(arr, getContext());
                fmBinding.rvNhaHangGanBan.setAdapter(adapter);
                fmBinding.rvNhaHangGanBan.setLayoutManager(new LinearLayoutManager(getContext()));
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
