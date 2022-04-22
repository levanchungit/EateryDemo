package com.example.eaterydemo.adapter;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.fragments.ADM_QuanLyCuaHangFM;
import com.example.eaterydemo.fragments.ADM_QuanLyCuaHangFMDirections;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminQuanLyCuaHangAdapter extends RecyclerView.Adapter<AdminQuanLyCuaHangAdapter.ViewHolder> {


    List<NhaHang> arr;
    List<NhaHang> getArr;
    Context context;

    public AdminQuanLyCuaHangAdapter(List<NhaHang> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage_NhaHang, iv_Edit, iv_Delete;
        TextView tvTenNhaHang_ItemAdmin, tvLoaiNhaHang_ItemAdmin, tvDiaChi_ItemAdmin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage_NhaHang = itemView.findViewById(R.id.ivImage_AdminQuanLy);
            tvTenNhaHang_ItemAdmin = itemView.findViewById(R.id.tv_AdminQuanly1);
            tvLoaiNhaHang_ItemAdmin = itemView.findViewById(R.id.tv_AdminQuanly2);
            tvDiaChi_ItemAdmin = itemView.findViewById(R.id.tv_AdminQuanly3);
            iv_Edit = itemView.findViewById(R.id.img_EditAdminQuanLy);
            iv_Delete = itemView.findViewById(R.id.img_DeleteAdminQuanLy);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_adminquanly, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NhaHang model = arr.get(position);
        Glide.with(context).load(model.getHinhAnh()).centerCrop().placeholder(R.drawable.img_error).into(holder.ivImage_NhaHang);
        holder.tvTenNhaHang_ItemAdmin.setText(model.getTenNH());
        holder.tvLoaiNhaHang_ItemAdmin.setText(model.getTenLoaiNH() + "");
        holder.tvDiaChi_ItemAdmin.setText(model.getDiaChi());

        //Nút chỉnh sửa
        holder.iv_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ADM_QuanLyCuaHangFM.MaNH = model.getMaNH();
                NavDirections action = ADM_QuanLyCuaHangFMDirections.actionAdminQuanLyCuaHangFMToADMChinhSuaThongTinNhaHangFM(ADM_QuanLyCuaHangFM.MaNH);
                Navigation.findNavController(view).navigate(action);
            }
        });

        //Nút xóa
        holder.iv_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xoá nhà hàng này không?");

                //Nút có
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        XoaNhaHang(model.getMaNH());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                //Nút không
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    private void XoaNhaHang(int MaNH) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.XoaNhaHangTheoMaNH(MaNH);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                getArr = (List<NhaHang>) response.body();
                refresh();
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return arr.size();
    }


    private void refresh(){
        arr.clear();
        arr.addAll(getArr);
        notifyDataSetChanged();
    }
}
