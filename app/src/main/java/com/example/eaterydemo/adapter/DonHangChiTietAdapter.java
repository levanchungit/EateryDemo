package com.example.eaterydemo.adapter;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eaterydemo.R;
import com.example.eaterydemo.fragments.DonHangFMDirections;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.DonHangChiTiet;
import com.example.eaterydemo.service.ServiceAPI;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonHangChiTietAdapter extends RecyclerView.Adapter<DonHangChiTietAdapter.ViewHolder> {
    List<DonHangChiTiet> arr;
    DonHang donHang;
    DonHangChiTiet donHangChiTiet;
    Context context;
    SimpleDateFormat sfd = new SimpleDateFormat("dd-MM");
    static NavController navController;
    View _view;
    DecimalFormat df = new DecimalFormat("#");
    DecimalFormat df2 = new DecimalFormat("#,###");



    public DonHangChiTietAdapter(List<DonHangChiTiet> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSL, tvTenMonAN, tvGiaMon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSL = itemView.findViewById(R.id.tvSoLuongMA_DHChiTiet);
            tvTenMonAN = itemView.findViewById(R.id.tvTenMon_DHChiTiet);
            tvGiaMon = itemView.findViewById(R.id.tvGiaTungMA_DHChiTiet);

        }

        public TextView getTvSL() {
            return tvSL;
        }

        public TextView getTvTenMonAN() {
            return tvTenMonAN;
        }

        public TextView getTvGiaMon() {
            return tvGiaMon;
        }
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_monan_donhang_chitiet, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        _view = parent;
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DonHangChiTietAdapter.ViewHolder holder, int position) {
        DonHangChiTiet model = arr.get(position);
        initNavController(_view);


        TextView tvSL = holder.getTvSL();
        TextView tvTenMonAN = holder.getTvTenMonAN();
        TextView tvGiaMon = holder.getTvGiaMon();

        tvSL.setText(df.format(model.getSL())+"x");
        tvTenMonAN.setText(model.getTenMA());
        tvGiaMon.setText(df2.format(model.getGiaMA()) + "đ");



    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}
