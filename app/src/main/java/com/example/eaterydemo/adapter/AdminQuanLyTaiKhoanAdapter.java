package com.example.eaterydemo.adapter;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.fragments.ADM_QuanLyTaiKhoanFMDirections;
import com.example.eaterydemo.fragments.DangNhapFM;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminQuanLyTaiKhoanAdapter extends RecyclerView.Adapter<AdminQuanLyTaiKhoanAdapter.ViewHolder> {
    List<TaiKhoan> arr;
    List<TaiKhoan> getArr;
    Context context;
    NavController navController;


    public AdminQuanLyTaiKhoanAdapter(List<TaiKhoan> arr, Context context) {
        this.arr = arr;
        this.context = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage_NhaHang, iv_Edit, iv_Delete;
        TextView tvTenTaiKhoan_ItemAdmin, tvSDT_ItemAdmin, tvDiaChi_ItemAdmin;

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
        View view = inflater.inflate(R.layout.item_adminquanly, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaiKhoan model = arr.get(position);
        Glide.with(context).load(model.getHinhAnh()).centerCrop().placeholder(R.drawable.img_error).into(holder.ivImage_NhaHang);
        holder.tvTenTaiKhoan_ItemAdmin.setText(arr.get(position).getHoTen());
        holder.tvSDT_ItemAdmin.setText(model.getSDT());
        holder.tvDiaChi_ItemAdmin.setText(model.getDiaChi());
        Log.e("user " + position, arr.get(position).getHoTen());

        //Nút chỉnh sửa
        holder.iv_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenTk = model.getTenTK();
                NavDirections action = ADM_QuanLyTaiKhoanFMDirections.actionAdminQuanLyTaiKhoanFMToADMChinhSuaThongTinTaiKhoanFM(tenTk);
                Navigation.findNavController(view).navigate(action);
            }
        });

        //Nút xóa
        holder.iv_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xoá tài khoản này không?");

                //Nút có
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        XoaTaiKhoan(DangNhapFM.TENTK, model.getTenTK());
                        Toast.makeText(context, "Xóa tài khoản thành công", Toast.LENGTH_SHORT).show();
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
    private void XoaTaiKhoan(String TenTKHT, String TenTK) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.XoaTaiKhoanTheoTenTK(TenTKHT, TenTK);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                getArr = (List<TaiKhoan>) response.body();
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