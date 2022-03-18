package com.example.eaterydemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.FragmentEditProfileBinding;

public class EditProfileActivity extends AppCompatActivity {
    FragmentEditProfileBinding fmEditProfileBinding;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fmEditProfileBinding = FragmentEditProfileBinding.inflate(getLayoutInflater());

        setContentView(fmEditProfileBinding.getRoot());

    }


    private void initNavController(View viewEditProfileBinding) {
        navController = Navigation.findNavController(viewEditProfileBinding);
    }

    private void initClick() {
        fmEditProfileBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}