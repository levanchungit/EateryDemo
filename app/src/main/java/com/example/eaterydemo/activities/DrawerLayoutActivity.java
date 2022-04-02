package com.example.eaterydemo.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.eaterydemo.R;
import com.example.eaterydemo.databinding.DrawerLayoutActivityBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class DrawerLayoutActivity extends AppCompatActivity {

    DrawerLayoutActivityBinding bindingMapping;
    AppBarConfiguration appBarConfiguration;
    DrawerLayout drawerLayout;
    BottomNavigationView navBot;
    NavController navController;
    NavigationView navView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingMapping = DrawerLayoutActivityBinding.inflate(getLayoutInflater());
        setContentView(bindingMapping.getRoot());
        initDrawerlayout();
        initAppBarConfiguration();
        initNavView();
        initBotNav();
        initNavController();
        initNavUI();
        initClick();
    }

    private void initDrawerlayout() {
        drawerLayout = bindingMapping.drawerLayout;

    }

    private void initNavView() {
        navView = bindingMapping.navView;
    }


    private void initAppBarConfiguration () {
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.menu_TrangChu, R.id.menu_YeuThich, R.id.menu_ThanhToan, R.id.menu_ThongTin)
                .setOpenableLayout(drawerLayout)
                .build();
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
        NavigationUI.setupWithNavController(navView, navController);
    }


    private void initClick () {

    }
    @Override
    public boolean onSupportNavigateUp() {
        initNavController();
        return NavigationUI.navigateUp(navController, drawerLayout) ||NavigationUI.navigateUp(navController,appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed () {
        super.onBackPressed();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int _id = item.getItemId();
        switch (_id){
            case R.id.menu_ThongTin:
                navBot.getMenu().findItem(R.id.menu_ThongTin).setChecked(true);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}