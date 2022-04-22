package com.example.eaterydemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.FragmentThanhtoanthanhcongBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ThanhToanThanhCongFM extends Fragment {
    FragmentThanhtoanthanhcongBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentThanhtoanthanhcongBinding.inflate(getLayoutInflater());

        //tắt bottom navigation
        BottomNavigationView navbar = getActivity().findViewById(R.id.navBot);
        navbar.setVisibility(View.GONE);

        initClick();
        initNavController(container);
        String tongTien = ThanhToanThanhCongFMArgs.fromBundle(getArguments()).getTongTien();
        fmBinding.txtTong.setText(tongTien);
//        Locale localeVN = new Locale("vi", "VN");
//        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
//        String str1 = currencyVN.format(tongTien);
        fmBinding.txtTong.setText(tongTien);
        fmBinding.txtTongTien.setText(tongTien);


        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        fmBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ThanhToanThanhCongFMDirections.actionThanhToanThanhCongFMToMenuThanhToan();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

}
