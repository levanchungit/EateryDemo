package com.example.eaterydemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.fragments.NhaHangChiTietFMDirections;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.model.ThongKe;

import java.text.DecimalFormat;
import java.util.List;

public class ThongKeAdapter extends RecyclerView.Adapter<ThongKeAdapter.ViewHolder> {

    ImageView img_ItemMonAn;
    TextView tvTenMonAn, tvDoanhThu;
    List<ThongKe> arr;
    Context context;
    DecimalFormat df = new DecimalFormat("#,###");
    public ThongKeAdapter(List<ThongKe> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenMonAn = itemView.findViewById(R.id.tvTenMonAn_ThongKe);
            tvDoanhThu = itemView.findViewById(R.id.tvTienTungMonAn_ThongKe);
            img_ItemMonAn = itemView.findViewById(R.id.iv_MonAn_ThongKe);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_thongke_monan, parent, false);
        ThongKeAdapter.ViewHolder viewHolder = new ThongKeAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThongKe model = arr.get(position);
        Glide.with(context).load(model.getHinhAnh()).centerCrop().into(img_ItemMonAn);
        tvTenMonAn.setText(model.getTenMA());
        tvDoanhThu.setText(df.format(model.getTongDoanhThu()) + "đ");
        Log.d("Hinh anh", model.getHinhAnh()+"");
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}
