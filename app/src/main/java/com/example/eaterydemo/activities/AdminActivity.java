package com.example.eaterydemo.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.ActivityAdminBinding;
import com.example.eaterydemo.databinding.ActivityChuNhaHangBinding;

public class AdminActivity extends AppCompatActivity {
    ActivityAdminBinding mainActivityBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(mainActivityBinding.getRoot());
    }
}