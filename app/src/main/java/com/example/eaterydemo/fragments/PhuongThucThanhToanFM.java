package com.example.eaterydemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.FragmentPhuongthucthanhtoanBinding;


public class PhuongThucThanhToanFM extends Fragment {
    FragmentPhuongthucthanhtoanBinding fmBinding;
    NavController navController;
    public static final String clientKey = "AeO4rt1TE9cwVRL1iyXGfqQZWhubDbsFEhEUTlNmaLrvXGGjpPm5TlT7ryRQD6-ovk9HPyIyMQ2jI0Hp";
    public static final int PAYPAL_REQUEST_CODE = 123;
    LinearLayout lnPTTTPayPal;
    ImageView ivHinhPayPal;
    View _view;
    TextView tvTenNhaHang_DHChiTiet, tvNgayMuaDHChiTiet, tvTongTien_DHChiTiet, tvMaDH_DHChiTiet, tvTenKH_DHChiTiet, tvSDT_DHChiTiet, tvDiaChi_DHChiTiet;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentPhuongthucthanhtoanBinding.inflate(getLayoutInflater());
        initNavController(container);
        _view = container;


        return fmBinding.getRoot();
    }

//    private static PayPalConfiguration config = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(clientKey);

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

//    private void getPayment() {
//
//        // Getting the amount from editText
//        String amount = "100";
//
//        // Creating a paypal payment on below line.
//        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(amount)), "USD", "Course Fees",
//                PayPalPayment.PAYMENT_INTENT_SALE);
//
//        // Creating Paypal Payment activity intent
//        Intent intent = new Intent(getContext(), PaymentActivity.class);
//
//        //putting the paypal configuration to the intent
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//
//        // Putting paypal payment to the intent
//        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
//
//        // Starting the intent activity for result
//        // the request code will be used on the method onActivityResult
//        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
//    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }


}
