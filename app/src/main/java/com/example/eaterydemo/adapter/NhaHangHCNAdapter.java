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

public class NhaHangHCNAdapter extends RecyclerView.Adapter<NhaHangHCNAdapter.ViewHolder> {

    ImageView imgImage_NhaHang, imgDanhGia_NhaHang;
    TextView tvTenNhaHang_NhaHang, tvLoaiNhaHang_NhaHang, tvDiaChi_NhaHang;
    List<NhaHang> arr;
    Context context;

    public NhaHangHCNAdapter(List<NhaHang> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgImage_NhaHang = itemView.findViewById(R.id.imgImage_NhaHang);
            imgDanhGia_NhaHang = itemView.findViewById(R.id.imgDanhGia_NhaHang);
            tvTenNhaHang_NhaHang = itemView.findViewById(R.id.tvTenNhaHang_NhaHang);
            tvLoaiNhaHang_NhaHang = itemView.findViewById(R.id.tvLoaiNhaHang_NhaHang);
            tvDiaChi_NhaHang = itemView.findViewById(R.id.tvDiaChi_NhaHang);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_nhahang, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NhaHangHCNAdapter.ViewHolder holder, int position) {
        NhaHang model = arr.get(position);
        Glide.with(context).load(model.getHinhAnh()).centerCrop().placeholder(R.drawable.img_error).into(imgImage_NhaHang);
        tvTenNhaHang_NhaHang.setText(model.getTenNH());
        tvLoaiNhaHang_NhaHang.setText(model.getLoaiNH() + "");
        tvDiaChi_NhaHang.setText(model.getDiaChi());
        if (model.getDanhGia() < 10) {
            imgDanhGia_NhaHang.setImageResource(R.drawable._1sao);
        } else if (model.getDanhGia() < 20) {
            imgDanhGia_NhaHang.setImageResource(R.drawable._2sao);
        } else if (model.getDanhGia() < 30) {
            imgDanhGia_NhaHang.setImageResource(R.drawable._3sao);
        } else if (model.getDanhGia() < 40) {
            imgDanhGia_NhaHang.setImageResource(R.drawable._4sao);
        } else {
            imgDanhGia_NhaHang.setImageResource(R.drawable._5sao);
        }
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


}
