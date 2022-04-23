package com.example.eaterydemo.model;

public class ThongKe {
    public int MaMA;
    public String TenMA;
    public String HinhAnh;
    public double TongDoanhThu;

    public ThongKe(int maMA, String tenMA, String hinhAnh, double tongDoanhThu) {
        MaMA = maMA;
        TenMA = tenMA;
        HinhAnh = hinhAnh;
        TongDoanhThu = tongDoanhThu;
    }

    public ThongKe() {
    }

    public int getMaMA() {
        return MaMA;
    }

    public void setMaMA(int maMA) {
        MaMA = maMA;
    }

    public String getTenMA() {
        return TenMA;
    }

    public void setTenMA(String tenMA) {
        TenMA = tenMA;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public double getTongDoanhThu() {
        return TongDoanhThu;
    }

    public void setTongDoanhThu(double tongDoanhThu) {
        TongDoanhThu = tongDoanhThu;
    }
}
