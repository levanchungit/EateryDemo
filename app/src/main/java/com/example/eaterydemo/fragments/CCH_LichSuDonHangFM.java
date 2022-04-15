package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eaterydemo.adapter.CCH_LichSuDonHangAdapter;
import com.example.eaterydemo.databinding.FragmentCchLichsudonhangBinding;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CCH_LichSuDonHangFM extends Fragment {
    FragmentCchLichsudonhangBinding fmBinding;
    NavController navController;
    View _view;
    List<DonHang> arr;
    CCH_LichSuDonHangAdapter adapter;

    List<DonHang> arrThem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentCchLichsudonhangBinding.inflate(getLayoutInflater());
        _view = container;
        fmBinding.txtdateLichSuCCH.setInputType(InputType.TYPE_NULL);
        fmBinding.ivLichSuCCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datetimepicker();

            }
        });
        fmBinding.txtdateLichSuCCH.setText("2022-03-21");

        fmBinding.txtdateLichSuCCH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                GetAllMonAnTheoNhaHang();
            }
        });


        return fmBinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showProgressDialog(getContext(), "Đang tải dữ liệu");
        GetAllMonAnTheoNhaHang();
    }

    private void GetAllMonAnTheoNhaHang() {
        String date = fmBinding.txtdateLichSuCCH.getText().toString().trim();
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.LichSuNhungDonHangTrong1NgayCuaNH(CCH_QuanLyNhaHangFM.MaNH,date);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arr = (List<DonHang>) response.body();
                adapter = new CCH_LichSuDonHangAdapter(arr, getContext());
                fmBinding.rvLichSuCCh.setAdapter(adapter);
                fmBinding.rvLichSuCCh.setLayoutManager(new LinearLayoutManager(getContext()));
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void datetimepicker(){

        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String thang;
                if (month < 10) {
                     thang = "0" + (month+1);
                }else {
                     thang = (month+1) +"";
                }
                    fmBinding.txtdateLichSuCCH.setText(year + "-" + thang + "-" + dayOfMonth);

            }
        };

        new DatePickerDialog(getContext() ,dateSetListener,calendar.get(calendar.YEAR),calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH)).show();
    }
    private void refreshRecycler(){
        arr.clear();
        arr.addAll(arrThem);
        adapter.notifyDataSetChanged();
    }

}
