package com.example.eaterydemo.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.MyViewPagerAdapter;
import com.example.eaterydemo.databinding.FragmentQuanlydonhangChucuahangBinding;
import com.example.eaterydemo.databinding.FragmentVechungtoiBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class CCH_QuanLyDonHangFM extends Fragment {
    FragmentQuanlydonhangChucuahangBinding fmBinding;
    NavController navController;
    View _view;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentQuanlydonhangChucuahangBinding.inflate(getLayoutInflater());
        initNavController(container);
        _view = container;


        return fmBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.tlQuanlyDonHang_ChuCuaHang);
        viewPager2 = view.findViewById(R.id.vpQuanlyDonHang_ChuCuaHang);
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getParentFragment());
        viewPager2.setAdapter(myViewPagerAdapter);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#1C1B1B"));
        tabLayout.setTabTextColors(Color.parseColor("#B8B1B1"), Color.parseColor("#1C1B1B"));
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Đang làm");
                    break;
                case 1:
                    tab.setText("Đã xong");
                    break;
            }
        }).attach();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }


}
