package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.ServiceAPI.BASE_Service;

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
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TrangChuFM extends Fragment {
    FragmentTrangchuBinding fmBinding;
    NavController navController;

    int slideShow[] = {R.drawable.khuyenmai1, R.drawable.khuyenmai2, R.drawable.khuyenmai3, R.drawable.khuyenmai4};
    int currentPageCunter = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentTrangchuBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        showProgressDialog(getContext(), "Đang tải dữ liệu");
        getAllNhaHang();
        getAllKhuyenMai();

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        //SLIDESHOW KHUYẾN MÃI
        slideShowKhuyenMai(fmBinding.vpKhuyenMai1,slideShow);
        slideShowKhuyenMai(fmBinding.vpKhuyenMai2,slideShow);

        fmBinding.ivLau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = TrangChuFMDirections.actionHomeFMToNhaHangFM2();
                navController.navigate(action);
            }
        });
    }

    private void slideShowKhuyenMai(ViewPager vp, int[] arr){
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

    private void getAllNhaHang() {
        //Gọi API trả về danh sách nhà hàng
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetAllNhaHang()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void getAllKhuyenMai() {
        //Gọi API trả về danh sách khuyến mãi
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetAllKhuyenMai()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse1, this::handleError1)
        );
    }

    private void handleResponse(ArrayList<NhaHang> arr) {
        try {
            // KHAO 20% VALENTINE TRẮNG
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            fmBinding.rvKhao20ValentineTrang.setLayoutManager(linearLayoutManager);
            NhaHangVuongAdapter adapter = new NhaHangVuongAdapter(arr,getContext());
            fmBinding.rvKhao20ValentineTrang.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        dismissProgressDialog();
    }

    private void handleResponse1(ArrayList<KhuyenMai> arr) {
        try {
            // Món Ngon Kèm Nước Sài Gòn
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            fmBinding.rvMonAnKemNuocSaiGon.setLayoutManager(linearLayoutManager);
            KhuyenMaiAdapter adapter = new KhuyenMaiAdapter(arr,getContext());
            fmBinding.rvMonAnKemNuocSaiGon.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        dismissProgressDialog();
    }


    private void handleError(Throwable error) {
        dismissProgressDialog();
        Toast.makeText(getContext(), "Lỗi KHAO 20% VALENTINE TRẮNG", Toast.LENGTH_SHORT).show();
    }
    private void handleError1(Throwable error) {
        dismissProgressDialog();
        Toast.makeText(getContext(), "Lỗi Món Ngon Kèm Nước Sài Gòn", Toast.LENGTH_SHORT).show();
    }
}
