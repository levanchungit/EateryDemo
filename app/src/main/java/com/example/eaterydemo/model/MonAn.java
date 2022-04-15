package com.example.eaterydemo.model;

public class MonAn {
    private int MaMA;
    private String TenMA;
    private double Gia;
    private String HinhAnh;
    private int MaNH;
    private String TenNH;

    public MonAn(int maMA, String tenMA, double gia, String hinhAnh, int maNH, String tenNH) {
        MaMA = maMA;
        TenMA = tenMA;
        Gia = gia;
        HinhAnh = hinhAnh;
        MaNH = maNH;
        TenNH = tenNH;
    }

    public MonAn(int maMA, String tenMA, double gia, String hinhAnh, int maNH) {
        MaMA = maMA;
        TenMA = tenMA;
        Gia = gia;
        HinhAnh = hinhAnh;
        MaNH = maNH;
    }

    public MonAn(String tenMA, double gia, String hinhAnh, int maNH) {

        TenMA = tenMA;
        Gia = gia;
        HinhAnh = hinhAnh;
        MaNH = maNH;
    }

    public MonAn(int maMA) {
        MaMA = maMA;
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

    public double getGia() {
        return Gia;
    }

    public void setGia(double gia) {
        Gia = gia;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getMaNH() {
        return MaNH;
    }

    public void setMaNH(int maNH) {
        MaNH = maNH;
    }

    public String getTenNH() { return TenNH; }

    public void setTenNH(String tenNH) { TenNH = tenNH; }
}
