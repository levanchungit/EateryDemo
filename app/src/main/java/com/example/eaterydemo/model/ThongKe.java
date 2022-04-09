package com.example.eaterydemo.model;

public class ThongKe {
    public int MaMA;
    public String TenMA;
    public String HinnhAnhMA;
    public Double TongDoanhThu;

    public ThongKe(int maMA, String tenMA, String hinhAnh, Double tongDoanhThu) {
        MaMA = maMA;
        TenMA = tenMA;
        HinnhAnhMA = hinhAnh;
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
        return HinnhAnhMA;
    }

    public void setHinhAnh(String hinhAnh) {
        HinnhAnhMA = hinhAnh;
    }

    public Double getTongDoanhThu() {
        return TongDoanhThu;
    }

    public void setTongDoanhThu(Double tongDoanhThu) {
        TongDoanhThu = tongDoanhThu;
    }
}
