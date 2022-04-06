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
import com.example.eaterydemo.model.DonHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.NumberFormat;
import java.util.Locale;


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
        DonHang DONHANG = ThanhToanThanhCongFMArgs.fromBundle(getArguments()).getDonHang();
        fmBinding.txtTong.setText(DONHANG.getTongTien()+"");
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(DONHANG.getTongTien());
        fmBinding.txtTong.setText(str1);
        fmBinding.txtTongTien.setText(str1);


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
