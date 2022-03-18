package com.example.eaterydemo.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {
    MainActivityBinding mainActivityBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(mainActivityBinding.getRoot());
    }
}
