package com.example.eaterydemo.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.FragmentIntroBinding;
import com.example.eaterydemo.databinding.FragmentSplashBinding;

import java.util.Random;


public class IntroFM extends Fragment {
    FragmentIntroBinding fmIntroBinding;
    NavController navController;
    int[] arr = {R.drawable.sp_lau, R.drawable.sp_com, R.drawable.sp_bunpho, R.drawable.sp_thucuong, R.drawable.sp_anvat, R.drawable.sp_dacsan, R.drawable.sp_thucannhanh, R.drawable.sp_dinhduong};
    Handler handler, handler1;
    Runnable runnable, runnable1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmIntroBinding = FragmentIntroBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);


        for (int j = 0; j < arr.length; j++) {
            int finalJ = j;
            handler = new Handler();
            handler.postDelayed(runnable = new Runnable() {
                @Override
                public void run() {
                    fmIntroBinding.ivIntro.setImageResource(arr[finalJ]);
                    new Handler().postDelayed(this,200);
                }
            },  0);
        }

        handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                NavDirections action = IntroFMDirections.actionIntroFMToProfileFM();
                navController.navigate(action);
                handler1.removeCallbacks(null);
                Log.e("HD2","HD2");

            }
        }, 5000);

        return fmIntroBinding.getRoot();
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
        handler1.removeCallbacks(null);
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

    }
}
