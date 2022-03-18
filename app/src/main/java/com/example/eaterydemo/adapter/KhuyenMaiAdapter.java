package com.example.eaterydemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eaterydemo.R;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.NhaHang;

import java.util.List;

public class KhuyenMaiAdapter extends RecyclerView.Adapter<KhuyenMaiAdapter.ViewHolder> {

    TextView tvTenKhuyenMai;
    ImageView ivHinhKhuyenMai_ChuNhat;
    List<KhuyenMai> arr;

    public KhuyenMaiAdapter(List<KhuyenMai> arr){
        this.arr = arr;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenKhuyenMai = itemView.findViewById(R.id.tvTenKhuyenMai);
            ivHinhKhuyenMai_ChuNhat = itemView.findViewById(R.id.ivHinhKhuyenMai_ChuNhat);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_khuyenmai_chunhat, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KhuyenMaiAdapter.ViewHolder holder, int position) {
        KhuyenMai model = arr.get(position);
        tvTenKhuyenMai.setText(model.getTenMaKhuyenMai());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


}
