package com.example.eaterydemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.FragmentHomeBinding;


public class HomeFM extends Fragment {
    FragmentHomeBinding fmBinding;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        initClick();
        initNavController(container);

//        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager() .findFragmentById(R.id.fcv_Home);
//        navController = navHostFragment.getNavController();

        return fmBinding.getRoot();
    }

    private void initNavController(View viewFmProfileBinding) {
        navController = Navigation.findNavController(viewFmProfileBinding);
    }


    private void initClick() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        NhaHangYeuThichFM NHYTFragment = new NhaHangYeuThichFM();
        manager.beginTransaction().replace(R.id.fcv_Home, NHYTFragment).commit();

//        fmBinding.btnvTrangChu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    FragmentTransaction transaction = manager.beginTransaction();
//                    case R.id.menu_TrangChu:
//                        fg=FirstFragment.newInstance();
//                        replaceFragment(fg);
//                        break;
//                    case R.id.menu_YeuThich:
//                        NavDirections action = TrangChuFMDirections.actionHomeFMToNhaHangYeuThichFM();
//                        navController.navigate(action);
//                        break;
//                    case R.id.menu_ThanhToan:
//                        Toast.makeText(getContext(), item.getTitle().toString(), Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.menu_ThongTin:
//                        Toast.makeText(getContext(), item.getTitle().toString(), Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return true;
//            }
//        });
    }
}
