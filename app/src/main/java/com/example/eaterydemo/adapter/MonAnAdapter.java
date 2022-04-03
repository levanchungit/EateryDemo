package com.example.eaterydemo.adapter;

import android.content.Context;
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
import com.example.eaterydemo.fragments.NhaHangFMDirections;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.model.NhaHang;

import java.util.List;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.ViewHolder> {

    ImageView img_ItemMonAn;
    TextView txt_TenMonAn, txt_GiaMonAn;
    RelativeLayout relativeLayout;
    List<MonAn> arr;
    Context context;

    public MonAnAdapter(List<MonAn> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_TenMonAn = itemView.findViewById(R.id.txt_TenMonAn_ItemMonAn);
            txt_GiaMonAn = itemView.findViewById(R.id.txt_GiaMonAn_ItemMonAn);
            img_ItemMonAn = itemView.findViewById(R.id.img_ItemMonAn);
            relativeLayout = itemView.findViewById(R.id.rlitem_monan);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_monan, parent, false);
        MonAnAdapter.ViewHolder viewHolder = new MonAnAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonAn model = arr.get(position);
        int i = (int) model.getGia();
        Glide.with(context).load(model.getHinhAnh()).centerCrop().placeholder(R.drawable.img_error).into(img_ItemMonAn);
        txt_TenMonAn.setText(model.getTenMA());
        txt_GiaMonAn.setText(i + " đ");
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int maMA = model.getMaMA();
                int maNH = model.getMaNH();
                NavDirections action = NhaHangChiTietFMDirections.actionNhaHangChiTietFMToMonAnChiTietFM(maNH, maMA);
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}
