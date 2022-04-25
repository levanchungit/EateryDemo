package com.example.eaterydemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.eaterydemo.Helper.AppInfo;
import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.DrawerLayoutActivityBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPaySDK;


public class DrawerLayoutActivity extends AppCompatActivity {

    DrawerLayoutActivityBinding bindingMapping;
    AppBarConfiguration appBarConfiguration;
//    DrawerLayout drawerLayout;
    BottomNavigationView navBot;
    NavController navController;
    NavigationView navView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingMapping = DrawerLayoutActivityBinding.inflate(getLayoutInflater());

        setContentView(bindingMapping.getRoot());

        initBotNav();
        initNavController();
        initNavUI();
        initClick();
        //       ZAlopay
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ZaloPaySDK.init(AppInfo.APP_ID, Environment.SANDBOX);
    }


    private void initBotNav () {
        navBot = bindingMapping.appBarLayout.navBot;
        navBot.setItemIconTintList(null);
    }

    private void initNavController () {
        navController = Navigation.findNavController(this, R.id.fmNavHostGraph);
    }

    private void initNavUI () {
        NavigationUI.setupWithNavController(navBot, navController);
//        NavigationUI.setupWithNavController(navView, navController);
    }


    private void initClick () {

    }
    @Override
    public boolean onSupportNavigateUp() {
        initNavController();
        return /*NavigationUI.navigateUp(navController, drawerLayout) ||*/NavigationUI.navigateUp(navController,appBarConfiguration) || super.onSupportNavigateUp();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int _id = item.getItemId();
        switch (_id){
            case R.id.menu_ThongTin:
                navBot.getMenu().findItem(R.id.menu_ThongTin).setChecked(true);
                break;

            case R.id.menu_ThanhToan:
                navBot.getMenu().findItem(R.id.menu_ThanhToan).setChecked(true);
                break;


            case R.id.menu_YeuThich:
                navBot.getMenu().findItem(R.id.menu_YeuThich).setChecked(true);
                break;

            case R.id.menu_TrangChu:
                navBot.getMenu().findItem(R.id.menu_TrangChu).setChecked(true);
                break;

            default: navBot.getMenu().findItem(R.id.menu_TrangChu).setChecked(true);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}