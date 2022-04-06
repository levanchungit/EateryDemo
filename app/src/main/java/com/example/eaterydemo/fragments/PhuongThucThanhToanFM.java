package com.example.eaterydemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.FragmentPhuongthucthanhtoanBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class PhuongThucThanhToanFM extends Fragment {
    FragmentPhuongthucthanhtoanBinding fmBinding;
    NavController navController;
    public static final String clientKey = "AeO4rt1TE9cwVRL1iyXGfqQZWhubDbsFEhEUTlNmaLrvXGGjpPm5TlT7ryRQD6-ovk9HPyIyMQ2jI0Hp";
    public static final int PAYPAL_REQUEST_CODE = 123;
    LinearLayout lnPTTTPayPal;
    ImageView ivHinhPayPal;
    View _view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentPhuongthucthanhtoanBinding.inflate(getLayoutInflater());
        _view = container;

        //tắt bottom navigation
        BottomNavigationView navbar = getActivity().findViewById(R.id.navBot);
        navbar.setVisibility(View.GONE);

        initClick();

        return fmBinding.getRoot();
    }

    private void initClick() {
        fmBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = PhuongThucThanhToanFMDirections.actionPhuongThucThanhToanFMToMenuThongTin();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lnPTTTPayPal = view.findViewById(R.id.lnPTTTPayPal);
        lnPTTTPayPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getPayment();
            }
        });
    }




}
