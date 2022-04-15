package com.example.eaterydemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.MonAn;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class CCH_LichSuDonHangAdapter extends RecyclerView.Adapter<CCH_LichSuDonHangAdapter.ViewHolder> {
    List<DonHang> arr;
    Context context;
    SimpleDateFormat sfd = new SimpleDateFormat("dd-MM");
    static NavController navController;
    View _view;
    DecimalFormat df = new DecimalFormat("#,###");
    public CCH_LichSuDonHangAdapter(List<DonHang> arr, Context context){
        this.arr = arr;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_donhang, parent, false);
        CCH_LichSuDonHangAdapter.ViewHolder viewHolder = new CCH_LichSuDonHangAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonHang model = arr.get(position);

        holder.tvMaDH_CCH.setText(model.getMaDonHang()+"");
        holder.tvTongTienVaSoLuong_DH_CCH.setText(model.getTongTien()+" | "+model.getCountSL()+"món");
        holder.tvTranThaiDH_CCH.setText(model.getTrangThaiDH()+"");
        String ngay = model.getNgayMua();
        holder.tvNgayDH_CCH.setText(ngay.substring(0,10));


        TextView tvMaDH_CCH = holder.getTvMaDH();
        TextView tvTienSLMonDH_CCH = holder.getTvTienSLMonDH();
        TextView tvNgayDH = holder.getTvNgayDH();
        TextView tvTrangThai = holder.getTvTrangThaiDH();

        ImageView ivDoneDonHang = holder.getIvTrangThaiDH();
//
//        tvMaDH_CCH.setText(model.getNameRes());
//        tvTienDH.setText(df.format(model.getTongTien())+ "đ");
//        tvSoLuongMon.setText(model.getCountSL() + " món");
//        tvNgayDH.setText( model.getNgayMua());


        int TT = model.getTrangThaiDH();
        if (TT == 3) {
            tvTrangThai.setText("Hủy bỏ");
            tvTrangThai.setTextColor(Color.RED);
            ivDoneDonHang.setImageResource(R.drawable.dahuydonhang);
        } else if (TT == 2){
            tvTrangThai.setText("Đã giao");
            tvTrangThai.setTextColor(Color.GREEN);
            ivDoneDonHang.setImageResource(R.drawable.donhangdagiao);
        }else if (TT == 1){
            tvTrangThai.setText("Chờ xác nhận");
            tvTrangThai.setTextColor(Color.CYAN);
            ivDoneDonHang.setImageResource(R.drawable.trangthaichoxacnhan);
        }
    }



    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMaDH_CCH,tvTongTienVaSoLuong_DH_CCH,tvTranThaiDH_CCH,tvNgayDH_CCH;
        ImageView ivTrangThaiDH_CCH;
        RelativeLayout Rl_donhang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaDH_CCH = itemView.findViewById(R.id.tvMaDH_CCH);
            tvTongTienVaSoLuong_DH_CCH = itemView.findViewById(R.id.tvTongTienVaSoLuong_DH_CCH);
            tvTranThaiDH_CCH = itemView.findViewById(R.id.tvTranThaiDH_CCH);
            tvNgayDH_CCH = itemView.findViewById(R.id.tvNgayDH_CCH);
            ivTrangThaiDH_CCH = itemView.findViewById(R.id.ivTrangThaiDH_CCH);
            Rl_donhang = itemView.findViewById(R.id.rlQuanLyDH_CCH);
        }
        public TextView getTvMaDH() {
            return tvMaDH_CCH;
        }

        public TextView getTvTienSLMonDH() {
            return tvTongTienVaSoLuong_DH_CCH;
        }

        public TextView getTvNgayDH() {
            return tvTranThaiDH_CCH;
        }

        public TextView getTvTrangThaiDH() {
            return tvNgayDH_CCH;
        }

        public ImageView getIvTrangThaiDH() {
            return ivTrangThaiDH_CCH;
        }

        public RelativeLayout getRl_donhang() {
            return Rl_donhang;
        }

    }
}
