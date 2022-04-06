package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eaterydemo.R;
import com.example.eaterydemo.adapter.NhaHangHCNYeuThichAdapter;
import com.example.eaterydemo.databinding.FragmentNhahangyeuthichBinding;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.service.ServiceAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NhaHangYeuThichFM extends Fragment {
    FragmentNhahangyeuthichBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentNhahangyeuthichBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

        GetAllNhaHangYeuThich();
        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }

    private void initClick() {

    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView navbar = getActivity().findViewById(R.id.navBot);
        navbar.setVisibility(View.VISIBLE);
    }

    private void GetAllNhaHangYeuThich() {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.GetAllNhaHangYeuThichCuaTaiKhoan(DangNhapFM.TENTK);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<NhaHang> arr = (List<NhaHang>) response.body();
                if(arr!=null){
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    fmBinding.rvNhaHangYeuThich.setLayoutManager(linearLayoutManager);
                    NhaHangHCNYeuThichAdapter adapter = new NhaHangHCNYeuThichAdapter(arr, getContext());
                    fmBinding.rvNhaHangYeuThich.setAdapter(adapter);
                    dismissProgressDialog();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
