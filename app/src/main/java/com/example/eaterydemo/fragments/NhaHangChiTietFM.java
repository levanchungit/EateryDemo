package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.MonAnAdapter;
import com.example.eaterydemo.databinding.FragmentNhahangchitietBinding;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.model.NhaHangYeuThich;
import com.example.eaterydemo.service.ServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NhaHangChiTietFM extends Fragment {
    FragmentNhahangchitietBinding fmBinding;
    NavController navController;
    int maNh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentNhahangchitietBinding.inflate(getLayoutInflater());
        initClick();
        GetAllMonAnNhaHangChiTiet();
        GetNhaHangChiTiet();
        initNavController(container);
        maNh = NhaHangChiTietFMArgs.fromBundle(getArguments()).getMaNh();

//        isYeuThich();

        showProgressDialog(getContext(), "Đang tải dữ liệu");

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void closeFragment() {
        getActivity().getFragmentManager().popBackStack();
    }

    private void initClick() {
        fmBinding.imgBackNhaHangChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFragment();
//                NavDirections action = NhaHangFMDirections.actionNhaHangFMToHomeFM();
//                navController.navigate(action);
//                NavDirections action = NhaHangChiTietFMDirections.actionNhaHangChiTietFMToNhaHangFM();
//                navController.navigate(action);
            }
        });

        fmBinding.imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemNhaHangYeuThich(new NhaHangYeuThich(DangNhapFM.TENTK, maNh));
            }
        });
    }

//    private void isYeuThich() {
//        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
//        int maNh = NhaHangChiTietFMArgs.fromBundle(getArguments()).getMaNh();
//        Call call = serviceAPI.GetNhaHangTheoMaNH(maNh);
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                NhaHang nhaHang = (NhaHang) response.body();
//                dismissProgressDialog();
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                dismissProgressDialog();
//                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void ThemNhaHangYeuThich(NhaHangYeuThich nhaHangYeuThich) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.ThemNhaHangYeuThich(nhaHangYeuThich);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Message message = (Message) response.body();
                Toast.makeText(requireContext(), message.getNotification(), Toast.LENGTH_SHORT).show();
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetNhaHangChiTiet() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetNhaHangTheoMaNH(maNh);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                NhaHang nhaHang = (NhaHang) response.body();
                fmBinding.txtTenNhaHangChiTiet.setText(nhaHang.getTenNH());
                fmBinding.txtDiaChiChiTiet.setText(nhaHang.getDiaChi());
                Glide.with(getContext()).load(nhaHang.getHinhAnh()).centerCrop().placeholder(R.drawable.img_error).into(fmBinding.imgNhaHangChiTiet);
                if (nhaHang.getDanhGia() < 10) {
                    fmBinding.imgDanhGiaChiTiet.setImageResource(R.drawable._1sao);
                } else if (nhaHang.getDanhGia() < 20) {
                    fmBinding.imgDanhGiaChiTiet.setImageResource(R.drawable._2sao);
                } else if (nhaHang.getDanhGia() < 30) {
                    fmBinding.imgDanhGiaChiTiet.setImageResource(R.drawable._3sao);
                } else if (nhaHang.getDanhGia() < 40) {
                    fmBinding.imgDanhGiaChiTiet.setImageResource(R.drawable._4sao);
                } else {
                    fmBinding.imgDanhGiaChiTiet.setImageResource(R.drawable._5sao);
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

    private void GetAllMonAnNhaHangChiTiet() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        int maNh = NhaHangChiTietFMArgs.fromBundle(getArguments()).getMaNh();
        Call call = serviceAPI.GetAllMonAnTheoNhaHang(maNh);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<MonAn> arr = (List<MonAn>) response.body();
                Log.d("arr", arr.size() + "");
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                fmBinding.rvMonAnNhaHangChiTiet.setLayoutManager(staggeredGridLayoutManager);
                MonAnAdapter adapter = new MonAnAdapter(arr, getContext());
                fmBinding.rvMonAnNhaHangChiTiet.setAdapter(adapter);
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
