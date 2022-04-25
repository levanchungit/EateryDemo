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
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eaterydemo.R;
import com.example.eaterydemo.fragments.CCH_QuanLyDonHangFMDirections;
import com.example.eaterydemo.model.DonHang;

import java.text.DecimalFormat;
import java.util.List;

public class CCH_ItemDangLam_QuanLyDonHangAdapter extends RecyclerView.Adapter<CCH_ItemDangLam_QuanLyDonHangAdapter.ViewHolder> {
    List<DonHang> arr;
    Context context;
    static NavController navController;
    View _view;
    DecimalFormat df = new DecimalFormat("#,###");

    public CCH_ItemDangLam_QuanLyDonHangAdapter(List<DonHang> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaDH, tvTienSLMonDH, tvNgayDH, tvTrangThaiDH;
        ImageView ivTrangThaiDH;
        RelativeLayout Rl_donhang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaDH = itemView.findViewById(R.id.tvMaDH_CCH);
            tvTienSLMonDH = itemView.findViewById(R.id.tvTongTienVaSoLuong_DH_CCH);
            tvNgayDH = itemView.findViewById(R.id.tvNgayDH_CCH);
            tvTrangThaiDH = itemView.findViewById(R.id.tvTranThaiDH_CCH);
            ivTrangThaiDH = itemView.findViewById(R.id.ivTrangThaiDH_CCH);
            Rl_donhang = itemView.findViewById(R.id.rlQuanLyDH_CCH);

        }

        public TextView getTvMaDH() {
            return tvMaDH;
        }

        public TextView getTvTienSLMonDH() {
            return tvTienSLMonDH;
        }

        public TextView getTvNgayDH() {
            return tvNgayDH;
        }

        public TextView getTvTrangThaiDH() {
            return tvTrangThaiDH;
        }

        public ImageView getIvTrangThaiDH() {
            return ivTrangThaiDH;
        }

        public RelativeLayout getRl_donhang() {
            return Rl_donhang;
        }
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_donhang, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        _view = parent;
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CCH_ItemDangLam_QuanLyDonHangAdapter.ViewHolder holder, int position) {
        DonHang model = arr.get(position);
        initNavController(_view);
        RelativeLayout rlDH = holder.getRl_donhang();

        rlDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = CCH_QuanLyDonHangFMDirections.actionCCHQuanLyDonHangFMToCCHChiTietDonHangFM(model);
                navController.navigate(action);

            }
        });
        TextView tvMaDH = holder.getTvMaDH();
        TextView tvTienSLDH = holder.getTvTienSLMonDH();
        TextView tvNgayDH = holder.getTvNgayDH();
        TextView tvTrangThai = holder.getTvTrangThaiDH();
        ImageView ivTrangThai = holder.getIvTrangThaiDH();

        tvMaDH.setText("#000" + model.getMaDonHang() + "");
        tvTienSLDH.setText(df.format(model.getTongTien()) + "đ | " + model.getCountSL() + " món");
        String ngay = model.getNgayMua();
        tvNgayDH.setText(ngay.substring(0, 10));
        tvTrangThai.setText("Chờ xác nhận");
        tvTrangThai.setTextColor(Color.RED);
        ivTrangThai.setImageResource(R.drawable.dahuydonhang);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}
