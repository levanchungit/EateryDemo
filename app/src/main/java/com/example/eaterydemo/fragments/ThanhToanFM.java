package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eaterydemo.Helper.AppInfo;
import com.example.eaterydemo.Helper.CreateOrder;
import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.GioHangAdapter;
import com.example.eaterydemo.databinding.FragmentThanhtoanBinding;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.DonHangChiTiet;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.service.ServiceAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class ThanhToanFM extends Fragment {
    FragmentThanhtoanBinding fmBinding;
    NavController navController;
    List<DonHangChiTiet> arr = new ArrayList<>();
    DonHang DONHANG;
    GioHangAdapter adapter;
    EditText diachi;
    List<KhuyenMai> arr2 = new ArrayList<>();
    String[] thanhtoan = {"VNĐ", "ZaLo Pay", "PayPal", "MoMo"};
    KhuyenMai khuyenmai = new KhuyenMai();
    List<DonHangChiTiet> arrDHCT = new ArrayList<>();
    KhuyenMai km;
    public static int maKMDH;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentThanhtoanBinding.inflate(getLayoutInflater());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, thanhtoan);
        fmBinding.spPhuongThucThanhToan.setAdapter(adapter);
        fmBinding.spPhuongThucThanhToan.setSelected(true);
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

                AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
                LayoutInflater inflater =  getActivity().getLayoutInflater();
                View v = inflater.inflate(R.layout.dialog_thaydoi_diachi_thanhtoan, null);
                diachi = v.findViewById(R.id.edtThayDoiDiaChi_ThanhToan);
                diachi.setText(fmBinding.txtDiaChiThanhToan.getText().toString());
                builder.setView(v);
                builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String diachi1 = diachi.getText().toString();
                        if (diachi1.length() == 0){
                            Toast.makeText(getContext(), "Vui lòng nhập địa chỉ thay đổi", Toast.LENGTH_SHORT).show();
                        }else{
                            ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
                            Call call = serviceAPI.CapNhatDiaChiGiaoHang(DONHANG.getMaDonHang(), diachi1);
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
                            dialogInterface.cancel();
                        }
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
                if (DONHANG != null) {
                    int _MaDH = DONHANG.getMaDonHang();
                    String _DiaChi = fmBinding.txtDiaChiThanhToan.getText().toString();
                    int _TrangThaiDH = 1;
                    float _TongTien = DONHANG.getTongTien();
                    String _TenTK = DONHANG.getTenTK();
                    CapNhatTrangThaiDonHangCuaTK(new DonHang(_MaDH, _DiaChi, _TrangThaiDH, _TongTien, _TenTK));
                    fmBinding.tvTranThaiMaKhuyenMai.setText("");

                }
            }
        });

//        Nhập mã khuyến mãi
        fmBinding.edtMaKhuyenMaiThanhToan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
                Call call = serviceAPI.GetAllKhuyenMai();
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        List<KhuyenMai> arr = (List<KhuyenMai>) response.body();
                        GetMaKhuyenMai(arr);
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


    private void GetMaKhuyenMai(List<KhuyenMai> arr) {
        String maKM = fmBinding.edtMaKhuyenMaiThanhToan.getText().toString();
        for (KhuyenMai khuyenMai : arr) {
            if (DONHANG.getMaNH() == khuyenMai.getMaNH()) {
                arr2.add(khuyenMai);
            }
        }
        for (KhuyenMai khuyenMai2 : arr2) {
            Log.d("maKM", khuyenMai2.getMaKM());
            if (khuyenMai2.getMaKM().equals(maKM) && khuyenMai2.getSL() >= 1) {
                int tienKM = khuyenMai2.getTienKM();
                maKMDH = tienKM;
                int i = (int) (DONHANG.getTongTien() - ((DONHANG.getTongTien() * tienKM) / 100));
                Log.d("Tien KM : ", DONHANG.getTongTien()+"");
                Log.d("Tien KM : ", i+"");
                //chuyển đổi đơn vị tiền tệ
                Locale localeVN = new Locale("vi", "VN");
                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                String str1 = currencyVN.format(i);
                fmBinding.txtTongTienThanhToan.setText(str1);
                khuyenmai = khuyenMai2;
                fmBinding.tvTranThaiMaKhuyenMai.setText("Đã áp dụng mã khuyến mãi giảm " + khuyenMai2.getTienKM() + "%");
                fmBinding.tvTranThaiMaKhuyenMai.setTextColor(Color.RED);
                break;
            } else {
                int i = (int) DONHANG.getTongTien();
                //chuyển đổi đơn vị tiền tệ
                Locale localeVN = new Locale("vi", "VN");
                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                String str1 = currencyVN.format(i);
                fmBinding.txtTongTienThanhToan.setText(str1);
                fmBinding.tvTranThaiMaKhuyenMai.setText("Mã khuyến mãi không tồn tại hoặc đã hết số lượng");
                fmBinding.tvTranThaiMaKhuyenMai.setTextColor(Color.RED);
            }
            break;
        }
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
                    arrDHCT = DONHANG.getDONHANGCHITIETs();
                    if (arrDHCT != null) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        fmBinding.rvDonHangThanhToan.setLayoutManager(linearLayoutManager);
                        adapter = new GioHangAdapter(arrDHCT, getContext(), fmBinding.txtTongTienThanhToan, fmBinding.txtTongThanhToan);
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
                        refresh();
                        int Sl = khuyenmai.getSL() - 1;
                        khuyenmai.setSL(Sl);

                        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
                        Call call1 = serviceAPI.ChinhSuaMaKhuyenMaiTheoNH(khuyenmai);
                        call1.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                Toast.makeText(getContext(), "cập nhật thành công", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                            }
                        });
                        if (fmBinding.spPhuongThucThanhToan.getSelectedItemPosition() == 1) {
                            CreateOrder orderApi = new CreateOrder();
                            int tien = (int) DONHANG.getTongTien();
                            Log.d("tien int : ", tien + "");
                            try {

                                JSONObject data = orderApi.createOrder(tien+"");
                                String code = data.getString("returncode");

                                if (code.equals("1")) {

                                    String token = data.getString("zptranstoken");

                                    ZaloPaySDK.getInstance().payOrder(getActivity(), token, "demozpdk://app", new PayOrderListener() {
                                        @Override
                                        public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                                            Toast.makeText(getActivity(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                                            NavDirections action = ThanhToanFMDirections.actionMenuThanhToanToThanhToanThanhCongFM(DONHANG);
                                            Navigation.findNavController(getView()).navigate(action);
                                        }

                                        @Override
                                        public void onPaymentCanceled(String zpTransToken, String appTransID) {
                                            Toast.makeText(getActivity(), "Thanh toán bị hủy", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                                            Toast.makeText(getActivity(), "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }


                    }
                }
            }



            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void refresh() {
        arrDHCT.clear();
        arrDHCT.addAll(arrDHCT);
        adapter.notifyDataSetChanged();
        GetThongTinDonHang();
        fmBinding.edtMaKhuyenMaiThanhToan.setText("");
        fmBinding.edtMaKhuyenMaiThanhToan.setFocusable(false);

    }

    @Override
    public void onResume() {
        super.onResume();
        //tắt bottom navigation
        BottomNavigationView navbar = getActivity().findViewById(R.id.navBot);
        navbar.setVisibility(View.VISIBLE);

    }

}