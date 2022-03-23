package com.example.eaterydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.model.NhaHang;

import java.util.List;

public class NhaHangVuongAdapter extends RecyclerView.Adapter<NhaHangVuongAdapter.ViewHolder> {

    TextView tvTenNhaHang, tvDanhGia;
    ImageView ivImage_NhaHangVuong;
    List<NhaHang> arr;
    Context context;

    public NhaHangVuongAdapter(List<NhaHang> arr, Context context){
        this.arr = arr;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenNhaHang = itemView.findViewById(R.id.tvTenNhaHang_NhaHangVuong);
            tvDanhGia = itemView.findViewById(R.id.tvDanhGia_NhaHangVuong);
            ivImage_NhaHangVuong = itemView.findViewById(R.id.ivImage_NhaHangVuong);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_nhahang_vuong, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NhaHangVuongAdapter.ViewHolder holder, int position) {
        NhaHang model = arr.get(position);
        Glide.with(context).load(model.getHinhAnh()).centerCrop().into(ivImage_NhaHangVuong);
        tvTenNhaHang.setText(model.getTenNH());
        if (model.getDanhGia() < 10) {
            tvDanhGia.setText("1");
        } else if (model.getDanhGia() < 20) {
            tvDanhGia.setText("2");
        } else if (model.getDanhGia() < 30) {
            tvDanhGia.setText("3");
        } else if (model.getDanhGia() < 40) {
            tvDanhGia.setText("4");
        } else {
            tvDanhGia.setText("5");
        }
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}
