package com.example.eaterydemo.model;

public class ThongKe {
    public int MaMA;
    public String TenMA;
    public String HinhAnh;
    public Double TongDoanhThu;

    public ThongKe(int maMA, String tenMA, String hinhAnh, Double tongDoanhThu) {
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

    public Double getTongDoanhThu() {
        return TongDoanhThu;
    }

    public void setTongDoanhThu(Double tongDoanhThu) {
        TongDoanhThu = tongDoanhThu;
    }
}
