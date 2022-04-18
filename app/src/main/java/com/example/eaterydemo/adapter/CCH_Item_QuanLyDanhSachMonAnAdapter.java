package com.example.eaterydemo.adapter;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.service.ServiceAPI;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CCH_Item_QuanLyDanhSachMonAnAdapter extends RecyclerView.Adapter<CCH_Item_QuanLyDanhSachMonAnAdapter.ViewHolder> {
    Context context;

    MonAn model;
    List<MonAn> arr;
    List<MonAn> arrRong = new ArrayList<>();

    public static int MaMA;

    CCH_Item_QuanLyDanhSachMonAnAdapter adapter;
    public CCH_Item_QuanLyDanhSachMonAnAdapter(List<MonAn> arr , Context context){
        this.arr = arr;
        this.context = context;
    }
    @NonNull
    @Override
    public CCH_Item_QuanLyDanhSachMonAnAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_quan_ly_danh_sach_mon_an, parent, false);
        CCH_Item_QuanLyDanhSachMonAnAdapter.ViewHolder viewHolder = new CCH_Item_QuanLyDanhSachMonAnAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         model = arr.get(position);
        Log.d("arrtrc : ", arr.size()+"");

        Glide.with(context).load(model.getHinhAnh()).into(holder.ivImage);
        holder.txtMonAn.setText(model.getTenMA());
        int i = (int) model.getGia();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(i);
        holder.txtPrice.setText(str1);
//        holder.txtPrice.setText(model.getGia()+"");
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogupdate();
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogConfirm();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMonAn,txtPrice;
        ImageView ivImage,imgUpdate,imgDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMonAn = itemView.findViewById(R.id.txtTenMon_QuanLyDanhSachMonAn);
            txtPrice = itemView.findViewById(R.id.txtPrice_QuanLyDanhSachMonAn);
            ivImage = itemView.findViewById(R.id.img_QuanLyDanhSachMonAn);
            imgUpdate = itemView.findViewById(R.id.imgupdate_QuanLyDanhSachMonAn);
            imgDelete = itemView.findViewById(R.id.imgDelete_QuanLyDanhSachMonAn);

        }
    }

    private void dialogupdate() {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_chinhsuaquanlydanhsachmonan,null);
        b.setView(view);
        Dialog dialog = b.create();

        EditText ten = view.findViewById(R.id.edtTenMon_QuanLyDanhSachMonAn);
        EditText gia = view.findViewById(R.id.edtPrice_QuanLyDanhSachMonAn);
        ImageView hinhanh = view.findViewById(R.id.img_ChinhSuaMonAn);
        Button btnChinhSua = view.findViewById(R.id.btnChinhMonAn);
        Button btnhuy = view.findViewById(R.id.btnHuydialog);

        ten.setText(model.getTenMA());
        gia.setText(model.getGia()+"");

        dialog.show();
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int maMA = model.getMaMA();
                int maNH = model.getMaNH();
                String tenMA = ten.getText().toString().trim();
                Double giaMA = Double.parseDouble(gia.getText().toString().trim());
                ChinhSuaMonAnTrongNhaHang(new MonAn(maMA,tenMA,giaMA,model.getHinhAnh(),maNH));
                dialog.dismiss();
            }
        });

//        btnhuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                dialog.dismiss();
//            }
//        });




    }
    private void diaLogConfirm() {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setTitle("Xác nhận");
        b.setMessage("Bạn có chắc chắn muốn xóa món ăn này không");
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                int maMa = model.getMaMA();
                XoaMonAnTrongNhaHang(maMa);
                refreshRecycler();
            }
        });
        b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }

    private void ChinhSuaMonAnTrongNhaHang(MonAn monAn) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.ChinhSuaMonAnTrongNhaHang(monAn);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrRong = (List<MonAn>) response.body();
                Log.d("arrsau : ", arrRong.size()+"");
                refreshRecycler();
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Log.e("erroo", t.toString());
            }
        });
    }

    private void XoaMonAnTrongNhaHang(int MaMA) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.XoaMonAnTrongNhaHang(MaMA);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrRong = (List<MonAn>) response.body();
                refreshRecycler();
                dismissProgressDialog();
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Log.e("erroo", t.toString());
            }
        });
    }
    private void refreshRecycler(){
        arr.clear();
        arr.addAll(arrRong);
        notifyDataSetChanged();
    }

}
