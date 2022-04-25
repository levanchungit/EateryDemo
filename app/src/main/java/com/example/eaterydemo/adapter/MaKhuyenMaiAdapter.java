package com.example.eaterydemo.adapter;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaKhuyenMaiAdapter extends RecyclerView.Adapter<MaKhuyenMaiAdapter.ViewHolder> {

    List<KhuyenMai> arr;
    List<KhuyenMai> arrRong;
    Context context;
//    KhuyenMai model;

    public MaKhuyenMaiAdapter(List<KhuyenMai> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_khuyenmai, parent, false);
        MaKhuyenMaiAdapter.ViewHolder viewHolder = new MaKhuyenMaiAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KhuyenMai model = arr.get(position);
        Glide.with(context).load(model.getHinhAnh()).into(holder.ivImage_MaKhuyenMai);
        holder.txtTenKhuyenMai.setText(model.getTenKhuyenMai() + "");
        holder.txtTien.setText(model.getTienKM()+"");
        Log.d("MaKM", model.getTenKhuyenMai() + "");
        holder.txtMa.setText(model.getMaKM());
        holder.txtSoLuongKhuyenMai.setText("SL:" + model.getSL() + "");


        holder.ivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogupdate(model);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogConfirm(model);
            }
        });


    }

    private void dialogupdate(KhuyenMai khuyenMai) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_chinhsuamakhuyenmai, null);
        b.setView(view);

        Dialog dialog = b.create();


        EditText ten = view.findViewById(R.id.txtTenKhuyenMaidialog);
        TextView ma = view.findViewById(R.id.txtMadialog);
        EditText sl = view.findViewById(R.id.txtSoLuongKhuyenMaidialog);
        EditText tien = view.findViewById(R.id.tienkhuyenmai);
        ImageView ivMaKM = view.findViewById(R.id.imgdialogEditMaKM);


        Button btnChinhSua = view.findViewById(R.id.btnChinhSuaMaKMdialog);
        Button btnHuyChinhSua = view.findViewById(R.id.btnHuyChinhSuaMaKMdialog);

        ten.setText(khuyenMai.getTenKhuyenMai());
        ma.setText(khuyenMai.getMaKM());
        sl.setText(khuyenMai.getSL()+"");
        tien.setText(khuyenMai.getTienKM()+"");
        Glide.with(context).load(khuyenMai.getHinhAnh()).centerCrop().placeholder(R.drawable.img_error).into(ivMaKM);
        dialog.show();
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maKm = khuyenMai.getMaKM();
                String tenKM = ten.getText().toString().trim();
                int slKM = Integer.parseInt(sl.getText().toString().trim());
                int tien1 = Integer.parseInt(tien.getText().toString().trim());
                int manh = khuyenMai.getMaNH();

                KhuyenMai khuyenMai1 = new KhuyenMai(maKm,tenKM,tien1,slKM,manh);
                ChinhSuaMaKhuyenMaiTheoNH(khuyenMai1);

                dialog.dismiss();
            }
        });
        btnHuyChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }




    private void diaLogConfirm(KhuyenMai khuyenMai) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setTitle("Thông báo");
        b.setMessage("Bạn có muốn xóa không?");
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String makm = khuyenMai.getMaKM();
                XoaKhuyenMai(makm);
            }
        });
        b.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }

    @Override
    public int getItemCount() {

        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenKhuyenMai, txtMa, txtSoLuongKhuyenMai,txtTien;
        ImageView ivImage_MaKhuyenMai, ivUpdate, ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenKhuyenMai = itemView.findViewById(R.id.txtTenKhuyenMai);
            ivImage_MaKhuyenMai = itemView.findViewById(R.id.imgMaKhuyen);
            txtMa = itemView.findViewById(R.id.txtMa);
            txtSoLuongKhuyenMai = itemView.findViewById(R.id.txtSoLuongKhuyenMai);
            txtTien = itemView.findViewById(R.id.txtTienKhuyenMaidialog);
            ivUpdate = itemView.findViewById(R.id.imgEdit);
            ivDelete = itemView.findViewById(R.id.imgDelete);
        }
    }

    private void ChinhSuaMaKhuyenMaiTheoNH(KhuyenMai khuyenMai) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.ChinhSuaMaKhuyenMaiTheoNH(khuyenMai);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrRong = (List<KhuyenMai>) response.body();
                dismissProgressDialog();
                refreshRecycler();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Log.e("erroo", t.toString());
            }
        });
    }

    private void XoaKhuyenMai(String _MaKM) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.XoaMaKhuyenMai(_MaKM);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrRong = (List<KhuyenMai>) response.body();
                refreshRecycler();
                dismissProgressDialog();
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Log.e("erroo", t.toString());
            }
        });
    }

    private void refreshRecycler(){
        arr.clear();
        arr.addAll(arrRong);
        notifyDataSetChanged();
    }

}
