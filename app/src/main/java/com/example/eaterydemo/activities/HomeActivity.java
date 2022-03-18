package com.example.eaterydemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.KhuyenMaiAdapter;
import com.example.eaterydemo.adapter.NhaHangAdapter;
import com.example.eaterydemo.adapter.SlideAdapter;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.NhaHang;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rvKhao20ValentineTrang, rvMonAnKemNuocSaiGon;
    ViewPager vpKhuyenMai1, vpKhuyenMai2;
    List<NhaHang> arrNH;
    List<KhuyenMai> arrKM;
    int slideShow[] = {R.drawable.khuyenmai1, R.drawable.khuyenmai2, R.drawable.khuyenmai3, R.drawable.khuyenmai4};
    int currentPageCunter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvKhao20ValentineTrang = findViewById(R.id.rvKhao20ValentineTrang);
        rvMonAnKemNuocSaiGon = findViewById(R.id.rvMonAnKemNuocSaiGon);
        vpKhuyenMai1 = findViewById(R.id.vpKhuyenMai1);
        vpKhuyenMai2 = findViewById(R.id.vpKhuyenMai2);

        arrNH = new ArrayList<>();
        arrKM = new ArrayList<>();

        //SLIDESHOW KHUYẾN MÃI
        slideShowKhuyenMai(vpKhuyenMai1,slideShow);
        slideShowKhuyenMai(vpKhuyenMai2,slideShow);

        // KHAO 20% VALENTINE TRẮNG
        arrNH.add(new NhaHang(1, "Nhà hàng 1", 4.1));
        arrNH.add(new NhaHang(2, "Nhà hàng 2", 4.2));
        arrNH.add(new NhaHang(3, "Nhà hàng 3", 4.3));
        arrNH.add(new NhaHang(4, "Nhà hàng 4", 4.4));
        arrNH.add(new NhaHang(5, "Nhà hàng 5", 4.5));
        arrNH.add(new NhaHang(6, "Nhà hàng 6", 4.5));
        arrNH.add(new NhaHang(7, "Nhà hàng 7", 4.5));
        arrNH.add(new NhaHang(8, "Nhà hàng 8", 4.5));
        arrNH.add(new NhaHang(9, "Nhà hàng 9", 4.5));
        arrNH.add(new NhaHang(10, "Nhà hàng 10", 4.5));
        arrNH.add(new NhaHang(11, "Nhà hàng 11", 4.5));
        NhaHangAdapter adapter = new NhaHangAdapter(arrNH);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvKhao20ValentineTrang.setLayoutManager(layoutManager);
        rvKhao20ValentineTrang.setAdapter(adapter);

        // Món Ngon Kèm Nước Sài Gòn
        arrKM.add(new KhuyenMai(1, "Khuyến mãi 1"));
        arrKM.add(new KhuyenMai(2, "Khuyến mãi 1"));
        arrKM.add(new KhuyenMai(3, "Khuyến mãi 1"));
        arrKM.add(new KhuyenMai(4, "Khuyến mãi 1"));
        arrKM.add(new KhuyenMai(5, "Khuyến mãi 1"));
        KhuyenMaiAdapter adapter1 = new KhuyenMaiAdapter(arrKM);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvMonAnKemNuocSaiGon.setLayoutManager(layoutManager1);
        rvMonAnKemNuocSaiGon.setAdapter(adapter1);

    }

    private void slideShowKhuyenMai(ViewPager vp, int[] arr){
        //SLIDER KHUYENMAI 1
        vp.setAdapter(new SlideAdapter(arr, HomeActivity.this));
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
}