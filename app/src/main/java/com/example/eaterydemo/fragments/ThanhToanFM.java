package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.GioHangAdapter;
import com.example.eaterydemo.databinding.FragmentThanhtoanBinding;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.DonHangChiTiet;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.service.ServiceAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThanhToanFM extends Fragment {
    FragmentThanhtoanBinding fmBinding;
    NavController navController;
    List<DonHangChiTiet> arr = new ArrayList<>();
    DonHang DONHANG;
    GioHangAdapter adapter;
    EditText diachi;

    KhuyenMai km;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentThanhtoanBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);
        GetThongTinDonHang();
        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {
        //Thay đổi địa chỉ

        fmBinding.tvThayDoiDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
                LayoutInflater inflater = ((Activity) getActivity()).getLayoutInflater();
                View v = inflater.inflate(R.layout.dialog_thaydoi_diachi_thanhtoan, null);
                diachi = v.findViewById(R.id.edtThayDoiDiaChi_ThanhToan);
                diachi.setText(fmBinding.txtDiaChiThanhToan.getText().toString());
                builder.setView(v);
                builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String diachi1 = diachi.getText().toString();
                        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
                        Call call = serviceAPI.CapNhatDiaChiGiaoHang(64, diachi1);
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                String diachimoi = (String) response.body();
                                fmBinding.txtDiaChiThanhToan.setText(diachimoi);
                                Toast.makeText(requireContext(), "cập nhật thành công", Toast.LENGTH_SHORT).show();
                                dismissProgressDialog();
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                dismissProgressDialog();
                                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.setPositiveButton("HỦY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //Thanh toán
        fmBinding.imgThanhToanThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DONHANG != null){
                    int _MaDH = DONHANG.getMaDonHang();
                    String _DiaChi = fmBinding.txtDiaChiThanhToan.getText().toString();
                    int _TrangThaiDH = 1;
                    float _TongTien = DONHANG.getTongTien();
                    String _TenTK = DONHANG.getTenTK();
                    CapNhatTrangThaiDonHangCuaTK(new DonHang(_MaDH, _DiaChi, _TrangThaiDH, _TongTien, _TenTK));
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //tắt bottom navigation
        BottomNavigationView navbar = getActivity().findViewById(R.id.navBot);
        navbar.setVisibility(View.VISIBLE);
    }

    private void GetThongTinDonHang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetDonHangTheoTK(DangNhapFM.TENTK);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                DonHang donHang = (DonHang) response.body();
                if (donHang != null) {
                    DONHANG = donHang;
                    int i = (int) donHang.getTongTien();
                    fmBinding.txtDiaChiThanhToan.setText(donHang.getDiaChi());
                    //chuyển đổi đơn vị tiền tệ
                    Locale localeVN = new Locale("vi", "VN");
                    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                    String str1 = currencyVN.format(i);
                    fmBinding.txtTongTienThanhToan.setText(str1);
                    fmBinding.txtTongThanhToan.setText(str1);

                    if (donHang.getDONHANGCHITIETs() != null) {
                        for (int j = 0; j < donHang.getDONHANGCHITIETs().size(); j++) {
                            arr.add(donHang.getDONHANGCHITIETs().get(j));
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        fmBinding.rvDonHangThanhToan.setLayoutManager(linearLayoutManager);
                        adapter = new GioHangAdapter(arr, getContext(),fmBinding.txtTongTienThanhToan,fmBinding.txtTongThanhToan);
                        fmBinding.rvDonHangThanhToan.setAdapter(adapter);
                        dismissProgressDialog();
                    }
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

    public void CapNhatTrangThaiDonHangCuaTK(DonHang donHang) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.CapNhatTrangThaiDonHangCuaTK(donHang);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                if (message != null) {
                    Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                    if (message.getStatus() == 1) {
                        Log.e("TrangThaiDH", "Đơn hàng đã được chuyển trạng thái là 1");
                        NavDirections action = ThanhToanFMDirections.actionMenuThanhToanToThanhToanThanhCongFM(DONHANG);
                        Navigation.findNavController(getView()).navigate(action);
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
