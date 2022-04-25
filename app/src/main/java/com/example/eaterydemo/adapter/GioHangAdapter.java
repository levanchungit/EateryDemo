package com.example.eaterydemo.adapter;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.fragments.ThanhToanFM;
import com.example.eaterydemo.model.DonHangChiTiet;
import com.example.eaterydemo.service.ServiceAPI;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {

    List<DonHangChiTiet> arr;
    List<DonHangChiTiet> arrDHCT;
    Context context;
    TextView txtTongTienThanhToan, txtTongThanhToan;
    int MaDH;
    int SL = 0;
    //chuyển đổi đơn vị tiền tệ
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    String tanghoacgiam = "";
    Double tongtienDH;

    public GioHangAdapter(List<DonHangChiTiet> arr, Context context, TextView txtTongTienThanhToan, TextView txtTongThanhToan) {
        this.arr = arr;
        this.context = context;
        this.txtTongTienThanhToan = txtTongTienThanhToan;
        this.txtTongThanhToan = txtTongThanhToan;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_ItemMonAnGioHang, img_TangSoLuong, img_GiamSoLuong, img_HuyMonAn;
        TextView txt_TenMonAn, txt_GiaMonAn, txt_SoLuong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_TenMonAn = itemView.findViewById(R.id.txt_TenMonAn_ItemThanhToan);
            txt_GiaMonAn = itemView.findViewById(R.id.txt_GiaMonAn_ItemThanhToan);
            img_ItemMonAnGioHang = itemView.findViewById(R.id.img_MonAn_ItemThanhToan);
            img_TangSoLuong = itemView.findViewById(R.id.img_TangSoLuong_ItemThanhToan);
            img_GiamSoLuong = itemView.findViewById(R.id.img_GiamSoLuong_ItemThanhToan);
            txt_SoLuong = itemView.findViewById(R.id.txt_SoLuong_ItemThanhToan);
            img_HuyMonAn = itemView.findViewById(R.id.img_Cancel_ItemThanhToan);
        }

        public ImageView getimg_ItemMonAnGioHang() {
            return img_ItemMonAnGioHang;
        }

        public ImageView getimg_TangSoLuong() {
            return img_TangSoLuong;
        }

        public ImageView getimg_GiamSoLuong() {
            return img_GiamSoLuong;
        }

        public TextView gettxt_TenMonAn() {
            return txt_TenMonAn;
        }

        public TextView gettxt_GiaMonAn() {
            return txt_GiaMonAn;
        }

        public TextView gettxt_SoLuong() {
            return txt_SoLuong;
        }

        public ImageView getimg_cancle() {
            return img_HuyMonAn;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_donhang_thanhtoan, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, int position) {
        DonHangChiTiet model = arr.get(position);
        int sl = (int) model.getSL();
        int i = (int) model.getGiaMA();
        MaDH = model.getMaDHCT();

        ImageView img_ItemMonAnGioHang = holder.getimg_ItemMonAnGioHang();
        ImageView img_TangSoLuong = holder.getimg_TangSoLuong();
        ImageView img_GiamSoLuong = holder.getimg_GiamSoLuong();
        ImageView img_HuyMonAN = holder.getimg_cancle();
        TextView txt_TenMonAn = holder.gettxt_TenMonAn();
        TextView txt_GiaMonAn = holder.gettxt_GiaMonAn();
        TextView txt_SoLuong = holder.gettxt_SoLuong();

        String str1 = currencyVN.format(i);

        Glide.with(context).load(model.getHinhAnh()).centerCrop().into(img_ItemMonAnGioHang);
        txt_TenMonAn.setText(model.getTenMA());
        txt_GiaMonAn.setText(str1);
        txt_SoLuong.setText(sl + "");


//      Cập nhật số lượng món ăn trong giỏ hàng
        //Giảm số lượng
        img_GiamSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tanghoacgiam = "giam";
                SL = (Integer.parseInt(txt_SoLuong.getText().toString()));
                Log.d("so luong", SL - 1 + "");
                txt_SoLuong.setText(SL - 1 + "");
                Log.d("MaDHCT, MAMA", model.getMaDHCT() + "," + model.getMaMA());
                ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
                Call call = serviceAPI.CapNhatSoLuongTangGiamMonAn(model.getMaDHCT(), model.getMaMA(), tanghoacgiam);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        double tongtien = (double) response.body();
                        if (tongtien == 1314.0) {
                            XoaMonAnTrongDonHang(model.getMaDHCT(), model.getMaMA());
                        }
                        tongtienDH = tongtien;
                        int tiensauKM = (int) (tongtien - ((tongtien * ThanhToanFM.maKMDH) / 100));
                        String str1 = currencyVN.format(tongtien);
                        String str2 = currencyVN.format(tiensauKM);
                        txtTongThanhToan.setText(str1);
                        txtTongTienThanhToan.setText(str2);
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        dismissProgressDialog();
                        Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        //Tăng số lượng
        img_TangSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tanghoacgiam = "tang";
                SL = (Integer.parseInt(txt_SoLuong.getText().toString()));
                Log.d("so luong", SL + 1 + "");
                txt_SoLuong.setText(SL + 1 + "");
                Log.d("MaDHCT, MAMA", model.getMaDHCT() + "," + model.getMaMA());
                ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
                Call call = serviceAPI.CapNhatSoLuongTangGiamMonAn(model.getMaDHCT(), model.getMaMA(), tanghoacgiam);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        double tongtien = (double) response.body();
                        int tiensauKM = (int) (tongtien - ((tongtien * ThanhToanFM.maKMDH) / 100));
                        tongtienDH = tongtien;
                        String str1 = currencyVN.format(tongtien);
                        String str2 = currencyVN.format(tiensauKM);
                        txtTongThanhToan.setText(str1);
                        txtTongTienThanhToan.setText(str2);
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        dismissProgressDialog();
                        Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        //Hủy món ăn
        img_HuyMonAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int _MaDHCT = model.getMaDHCT();
                int _MaMA = model.getMaMA();
                XoaMonAnTrongDonHang(_MaDHCT, _MaMA);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    private void XoaMonAnTrongDonHang(int MaDHCT, int MaMA) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.XoaMonAnTrongDonHang(MaDHCT, MaMA);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrDHCT = (List<DonHangChiTiet>) response.body();
                refresh();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetTongTienCuaDonHang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetTongTienCuaDonHang(MaDH);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                double TongTien = (double) response.body();
                String str1 = currencyVN.format(TongTien);
                int tiensauKM = (int) (TongTien - ((TongTien * ThanhToanFM.maKMDH) / 100));
                String str2 = currencyVN.format(tiensauKM);
                Log.d("Tien KM : ", TongTien + "");
                Log.d("Tien KM : ", str2 + "");
                txtTongThanhToan.setText(str1);
                txtTongTienThanhToan.setText(str2);

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void refresh() {
        arr.clear();
        arr.addAll(arrDHCT);
        GetTongTienCuaDonHang();
        notifyDataSetChanged();
    }
}
