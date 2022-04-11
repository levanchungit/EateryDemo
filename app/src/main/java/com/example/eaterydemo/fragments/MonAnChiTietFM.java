package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.MonAnChiTietAdapter;
import com.example.eaterydemo.databinding.FragmentMonanchitietBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonAnChiTietFM extends Fragment {
    FragmentMonanchitietBinding fmBinding;
    NavController navController;
    MonAn monAn;
    String _TENTK;
    int _MaMA;
    int _SL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentMonanchitietBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        GetMonAnChiTiet();
        GetAllMonAnNhaHangChiTiet();

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

        fmBinding.btnThemMonAnMonAnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _TENTK = DangNhapFM.TENTK;
                _MaMA = monAn.getMaMA();
                _SL = Integer.parseInt(fmBinding.txtSoLuongChiTiet.getText().toString());
                ThemMonAnVaoGioHang();
            }
        });

    }

    private void GetMonAnChiTiet() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        int maMonAn = MonAnChiTietFMArgs.fromBundle(getArguments()).getMaMA();
        Call call = serviceAPI.GetMonAnTheoMaMA(maMonAn);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                monAn = (MonAn) response.body();
                int i = (int) monAn.getGia();
                fmBinding.txtTenMonAnChiTiet.setText(monAn.getTenMA());
                fmBinding.txtGiaMonAnChiTiet.setText(i + " đ");
                fmBinding.txtTenNhaHangChiTiet2.setText(monAn.getTenNH());
                Glide.with(getContext()).load(monAn.getHinhAnh()).centerCrop().placeholder(R.drawable.img_error).into(fmBinding.imgMonAnChiTiet);

                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetAllMonAnNhaHangChiTiet() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        int maNH = MonAnChiTietFMArgs.fromBundle(getArguments()).getMaNH();
        Call call = serviceAPI.GetAllMonAnTheoNhaHang(maNH);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<MonAn> arr = (List<MonAn>) response.body();
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                fmBinding.rvMonAnMonAnChiTiet.setLayoutManager(staggeredGridLayoutManager);
                MonAnChiTietAdapter adapter = new MonAnChiTietAdapter(arr, getContext());
                fmBinding.rvMonAnMonAnChiTiet.setAdapter(adapter);
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ThemMonAnVaoGioHang() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.ThemMonAnVaoGioHang(_TENTK, _MaMA, _SL);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                //2, "Món ăn khác nhà hàng"
                //1, "Thêm món ăn vào giỏ hàng thành công"
                //0, "Món ăn đã tồn tại trong giỏ hàng"
                if(message.getStatus() != 2){
                    Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                }

                if (message.getStatus() == 2) {
                    diaLogConfirm();
                }

                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void XoaDonHangKhiDatMonAnKhacNhaHang(String _TenTK) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.XoaDonHangKhiDatMonAnKhacNhaHang(_TenTK);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
//                Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                //1: "Xoá DHCT và Đơn hàng thành công" và tạo lại đơn hàng
                if (message.getStatus() == 1) {
                    ThemMonAnVaoGioHang();
                }
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void diaLogConfirm() {
        AlertDialog.Builder b = new AlertDialog.Builder(getContext());
        b.setTitle("Xác nhận");
        b.setMessage("Bạn đang có một đơn hàng ở nhà nhà khác. Bạn có muốn xoá đơn hàng đó và tạo đơn hàng mới với món ăn này?");
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                XoaDonHangKhiDatMonAnKhacNhaHang(_TENTK);
            }
        });
        b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog al = b.create();
        al.show();
    }
}
