package com.example.eaterydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.model.NhaHang;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NhaHangHCNAdapter extends RecyclerView.Adapter<NhaHangHCNAdapter.ViewHolder> implements Filterable {


    List<NhaHang> arrNH;
    ArrayList<NhaHang> arrNHFiltered;
    Context context;
    NavController navController;

    public NhaHangHCNAdapter(List<NhaHang> arr, Context context) {
        this.arrNH = arr;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage_NhaHang, ivDanhGia_ItemNhaHang;
        TextView tvTenNhaHang_ItemNhaHang, tvLoaiNhaHang_ItemNhaHang, tvDiaChi_ItemNhaHang;
        RelativeLayout rlitem_nhahanghcn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage_NhaHang = itemView.findViewById(R.id.ivImage_NhaHang);
            ivDanhGia_ItemNhaHang = itemView.findViewById(R.id.ivDanhGia_ItemNhaHang);
            tvTenNhaHang_ItemNhaHang = itemView.findViewById(R.id.tvTenNhaHang_ItemNhaHang);
            tvLoaiNhaHang_ItemNhaHang = itemView.findViewById(R.id.tvLoaiNhaHang_ItemNhaHang);
            tvDiaChi_ItemNhaHang = itemView.findViewById(R.id.tvDiaChi_ItemNhaHang);
            rlitem_nhahanghcn = itemView.findViewById(R.id.rlitem_nhahanghcn);
        }

        public ImageView getivImage_NhaHang(){
            return ivImage_NhaHang;
        }
        public ImageView getivDanhGia_ItemNhaHang(){
            return ivDanhGia_ItemNhaHang;
        }
        public TextView gettvTenNhaHang_ItemNhaHang(){
            return tvTenNhaHang_ItemNhaHang;
        }
        public TextView gettvLoaiNhaHang_ItemNhaHang(){
            return tvLoaiNhaHang_ItemNhaHang;
        }
        public TextView gettvDiaChi_ItemNhaHang(){
            return tvDiaChi_ItemNhaHang;
        }
        public RelativeLayout getrlitem_nhahanghcn(){
            return rlitem_nhahanghcn;
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
        NhaHang model = arrNH.get(position);
        ImageView ivImage_NhaHang = holder.getivImage_NhaHang();
        ImageView ivDanhGia_ItemNhaHang = holder.getivDanhGia_ItemNhaHang();
        TextView tvTenNhaHang_ItemNhaHang = holder.gettvTenNhaHang_ItemNhaHang();
        TextView tvLoaiNhaHang_ItemNhaHang = holder.gettvLoaiNhaHang_ItemNhaHang();
        TextView tvDiaChi_ItemNhaHang = holder.gettvDiaChi_ItemNhaHang();
        RelativeLayout rlitem_nhahanghcn = holder.getrlitem_nhahanghcn();

        Glide.with(context).load(model.getHinhAnh()).centerCrop().placeholder(R.drawable.img_error).into(ivImage_NhaHang);
        tvTenNhaHang_ItemNhaHang.setText(model.getTenNH());
        tvLoaiNhaHang_ItemNhaHang.setText(model.getTenLoaiNH() + "");
        tvDiaChi_ItemNhaHang.setText(model.getDiaChi());
        if (model.getDanhGia() < 10) {
            ivDanhGia_ItemNhaHang.setImageResource(R.drawable._1sao);
        } else if (model.getDanhGia() < 20) {
            ivDanhGia_ItemNhaHang.setImageResource(R.drawable._2sao);
        } else if (model.getDanhGia() < 30) {
            ivDanhGia_ItemNhaHang.setImageResource(R.drawable._3sao);
        } else if (model.getDanhGia() < 40) {
            ivDanhGia_ItemNhaHang.setImageResource(R.drawable._4sao);
        } else {
            ivDanhGia_ItemNhaHang.setImageResource(R.drawable._5sao);
        }
        rlitem_nhahanghcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavDirections action = NhaHangFMDirections.actionNhaHangFMToNhaHangChiTietFM();
//                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrNH.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = arrNH.size();
                    filterResults.values = arrNH;

                } else {
                    List<NhaHang> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (NhaHang itemsModel : arrNH) {
                        String title = itemsModel.getTenNH();
                        if (title.toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemsModel);
                        }else if(removeAccent(title).toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrNHFiltered = (ArrayList<NhaHang>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replace('đ','d').replace('Đ','D');
    }

}
