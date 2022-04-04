package com.example.eaterydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.fragments.NhaHangFMDirections;
import com.example.eaterydemo.fragments.NhaHangYeuThichFMDirections;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.model.TaiKhoan;

import java.util.List;

public class AdminQuanLyTaiKhoanAdapter extends RecyclerView.Adapter<AdminQuanLyTaiKhoanAdapter.ViewHolder> {

    ImageView ivImage_NhaHang, iv_Edit, iv_Delete;
    TextView tvTenTaiKhoan_ItemAdmin, tvSDT_ItemAdmin, tvDiaChi_ItemAdmin;
    List<TaiKhoan> arr;
    Context context;
    NavController navController;

    public AdminQuanLyTaiKhoanAdapter(List<TaiKhoan> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage_NhaHang = itemView.findViewById(R.id.ivImage_AdminQuanLy);
            tvTenTaiKhoan_ItemAdmin = itemView.findViewById(R.id.tv_AdminQuanly1);
            tvSDT_ItemAdmin = itemView.findViewById(R.id.tv_AdminQuanly2);
            tvDiaChi_ItemAdmin = itemView.findViewById(R.id.tv_AdminQuanly3);
            iv_Edit = itemView.findViewById(R.id.img_EditAdminQuanLy);
            iv_Delete = itemView.findViewById(R.id.img_DeleteAdminQuanLy);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaiKhoan model = arr.get(position);
        Glide.with(context).load(model.getHinhAnh()).centerCrop().placeholder(R.drawable.img_error).into(ivImage_NhaHang);
        tvTenTaiKhoan_ItemAdmin.setText(model.getHoTen());
        tvSDT_ItemAdmin.setText(model.getSDT());
        tvDiaChi_ItemAdmin.setText(model.getDiaChi());

        //Nút chỉnh sửa
        iv_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Nút xóa
        iv_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


}