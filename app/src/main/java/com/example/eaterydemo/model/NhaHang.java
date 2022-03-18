package com.example.eaterydemo.model;

public class NhaHang{
    private int maNhaHang;
    private String tenNhaHang;
    private double danhGia;

    public NhaHang(int maNhaHang, String tenNhaHang, double danhGia) {
        this.maNhaHang = maNhaHang;
        this.tenNhaHang = tenNhaHang;
        this.danhGia = danhGia;
    }

    public int getMaNhaHang() {
        return maNhaHang;
    }

    public void setMaNhaHang(int maNhaHang) {
        this.maNhaHang = maNhaHang;
    }

    public String getTenNhaHang() {
        return tenNhaHang;
    }

    public void setTenNhaHang(String tenNhaHang) {
        this.tenNhaHang = tenNhaHang;
    }

    public double getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }
}
