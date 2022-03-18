package com.example.eaterydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eaterydemo.R;
import com.example.eaterydemo.model.NhaHang;

import java.util.ArrayList;
import java.util.List;

public class NhaHangAdapter extends RecyclerView.Adapter<NhaHangAdapter.ViewHolder> {

    TextView tvTenNhaHang, tvDanhGia;
    ImageView ivHinhQuanAn_Vuong;
    List<NhaHang> arr;

    public NhaHangAdapter(List<NhaHang> arr){
        this.arr = arr;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenNhaHang = itemView.findViewById(R.id.tvTenNhaHang);
            tvDanhGia = itemView.findViewById(R.id.tvDanhGia);
            ivHinhQuanAn_Vuong = itemView.findViewById(R.id.ivHinhQuanAn_Vuong);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_quanan_vuong, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NhaHangAdapter.ViewHolder holder, int position) {
        NhaHang model = arr.get(position);
        tvTenNhaHang.setText(model.getTenNhaHang());
        tvDanhGia.setText(String.format("%.0f",model.getDanhGia()));
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


}
