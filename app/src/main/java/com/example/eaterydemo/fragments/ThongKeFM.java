package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.ThongKeAdapter;
import com.example.eaterydemo.databinding.FragmentThongkeBinding;
import com.example.eaterydemo.model.ThongKe;
import com.example.eaterydemo.service.ServiceAPI;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThongKeFM extends Fragment {
    FragmentThongkeBinding fmBinding;
    NavController navController;
    View _view;
    private List<ThongKe> arr;
    ThongKeAdapter adapter;
    EditText edtTuNgay, edtDenNgay;
    Calendar calendar = Calendar.getInstance();
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    int x = 0;
    SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentThongkeBinding.inflate(getLayoutInflater());
        initNavController(container);
        _view = container;
        return fmBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavController(_view);
        edtTuNgay = view.findViewById(R.id.edtTuNgay_ThongKe);
        edtDenNgay = view.findViewById(R.id.edtDenNgay_ThongKe);
        fmBinding.edtTuNgayThongKe.setFocusable(false);
        fmBinding.edtDenNgayThongKe.setFocusable(false);

        thongKeTongDoanhThuNhaHangTheoNgay();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void thongKeTongDoanhThuNhaHangTheoNgay() {
        fmBinding.ivDateTimePickerTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        x = month + 1;
                        String thang = "";
                        String ngay = "";
                        if (x < 10) {
                            thang = "0" + x;
                        } else {
                            thang = x + "";
                        }
                        if (dayOfMonth < 10) {
                            ngay = "0" + dayOfMonth;
                        } else {
                            ngay = dayOfMonth + "";
                        }
                        fmBinding.edtTuNgayThongKe.setText(year + "-" + thang + "-" + ngay);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        fmBinding.ivDateTimePickerDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        x = month + 1;
                        String thang = "";
                        String ngay = "";
                        if (x < 10) {
                            thang = "0" + x;
                        } else {
                            thang = x + "";
                        }
                        if (dayOfMonth < 10) {
                            ngay = "0" + dayOfMonth;
                        } else {
                            ngay = dayOfMonth + "";
                        }
                        fmBinding.edtDenNgayThongKe.setText(year + "-" + thang + "-" + ngay);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        fmBinding.edtTuNgayThongKe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String tuNgay = edtTuNgay.getText().toString();
                String denNgay = edtDenNgay.getText().toString();
                ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
                Call call = serviceAPI.GetTongDoanhThuDonHangTheoNH(CCH_QuanLyNhaHangFM.MaNH, tuNgay, denNgay);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Double TongTien = (Double) response.body();
                        if (TongTien != null) {
                            String str1 = currencyVN.format(TongTien);
                            Log.d("tong tien", str1);
                            fmBinding.tvTongTienDoanhThuThongKe.setText(str1);
                            thongKeTongDoanhThuTungMonAn(tuNgay, denNgay);

                        }

                        dismissProgressDialog();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        dismissProgressDialog();
                        Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        fmBinding.edtDenNgayThongKe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String tuNgay = edtTuNgay.getText().toString();
                String denNgay = edtDenNgay.getText().toString();
                ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
                Call call = serviceAPI.GetTongDoanhThuDonHangTheoNH(CCH_QuanLyNhaHangFM.MaNH, tuNgay, denNgay);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Double TongTien = (Double) response.body();
                        if (TongTien != null) {
                            String str1 = currencyVN.format(TongTien);
                            Log.d("tong tien", str1);
                            fmBinding.tvTongTienDoanhThuThongKe.setText(str1);
                            thongKeTongDoanhThuTungMonAn(tuNgay, denNgay);


                        }
                        dismissProgressDialog();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        dismissProgressDialog();
                        Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void thongKeTongDoanhThuTungMonAn(String tungay, String denngay) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Log.e("ngaybatdau", tungay + " ngay ket thuc : " + denngay);
        Call call = serviceAPI.GetTongDoanhThuCuaTungMonAnTheoNH(CCH_QuanLyNhaHangFM.MaNH, tungay, denngay);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arr = (List<ThongKe>) response.body();
                Log.d("arr", arr.size() + "");
                adapter = new ThongKeAdapter(arr, getContext());
                fmBinding.rvThongKe.setAdapter(adapter);
                fmBinding.rvThongKe.setLayoutManager(new LinearLayoutManager(getContext()));
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}