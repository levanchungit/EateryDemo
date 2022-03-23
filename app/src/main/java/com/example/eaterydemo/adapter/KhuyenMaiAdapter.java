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
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.NhaHang;

import java.util.List;

public class KhuyenMaiAdapter extends RecyclerView.Adapter<KhuyenMaiAdapter.ViewHolder> {

    TextView tvMaKhuyenMai_KhuyenMai;
    ImageView ivImage_KhuyenMai;
    List<KhuyenMai> arr;
    Context context;

    public KhuyenMaiAdapter(List<KhuyenMai> arr, Context context){
        this.arr = arr;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaKhuyenMai_KhuyenMai = itemView.findViewById(R.id.tvMaKhuyenMai_KhuyenMai);
            ivImage_KhuyenMai = itemView.findViewById(R.id.ivImage_KhuyenMai);
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
        Glide.with(context).load("https://res.cloudinary.com/dq7xnkfde/image/upload/v1647938606/khuyenmai1_yyso59.jpg").into(ivImage_KhuyenMai);
        tvMaKhuyenMai_KhuyenMai.setText("Nhập "+model.getMaKM());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


}
