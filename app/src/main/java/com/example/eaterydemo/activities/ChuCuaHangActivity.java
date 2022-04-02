package com.example.eaterydemo.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eaterydemo.databinding.ActivityChuNhaHangBinding;

public class ChuCuaHangActivity extends AppCompatActivity {
    ActivityChuNhaHangBinding mainActivityBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = ActivityChuNhaHangBinding.inflate(getLayoutInflater());
        setContentView(mainActivityBinding.getRoot());
    }
}