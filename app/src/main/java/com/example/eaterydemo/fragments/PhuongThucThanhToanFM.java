package com.example.eaterydemo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.eaterydemo.Helper.CreateOrder;
import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.FragmentPhuongthucthanhtoanBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;


public class PhuongThucThanhToanFM extends Fragment {
    FragmentPhuongthucthanhtoanBinding fmBinding;
    NavController navController;
    LinearLayout lnPTTTPayPal;
    ImageView ivHinhPayPal;
    View _view;
    String amount = "10000";

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
                CreateOrder orderApi = new CreateOrder();
                try {
                    JSONObject data = orderApi.createOrder(amount);
                    String code = data.getString("returncode");
                    if (code.equals("1")) {

                        String token = data.getString("zptranstoken");

                        ZaloPaySDK.getInstance().payOrder(getActivity(), token, "demozpdk://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                                Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onPaymentCanceled(String zpTransToken, String appTransID) {
                                Toast.makeText(getContext(), "Thanh toán bị hủy", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                                Toast.makeText(getContext(), "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null && getActivity().getIntent().hasExtra("")) {
            // do whatever needed
        }
    }
}
