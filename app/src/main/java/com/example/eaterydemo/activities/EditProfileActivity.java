package com.example.eaterydemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

<<<<<<< HEAD
import com.example.eaterydemo.databinding.FragmentChinhsuaThongtinBinding;


public class EditProfileActivity extends AppCompatActivity {
    FragmentChinhsuaThongtinBinding fmEditProfileBinding;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fmEditProfileBinding = FragmentChinhsuaThongtinBinding.inflate(getLayoutInflater());

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
=======
import com.example.eaterydemo.R;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
>>>>>>> parent of 01fcdb4 (Merge branch 'main' of https://github.com/levanchung0808/EateryDemo)
    }
}