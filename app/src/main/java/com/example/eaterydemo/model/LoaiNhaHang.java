package com.example.eaterydemo.model;

public class LoaiNhaHang {
    private String MaLoaiNH;
    private String TenLoaiNH;
    private NhaHang NhaHang;

    public LoaiNhaHang(String maLoaiNH, String tenLoaiNH, com.example.eaterydemo.model.NhaHang nhaHang) {
        MaLoaiNH = maLoaiNH;
        TenLoaiNH = tenLoaiNH;
        NhaHang = nhaHang;
    }

    public String getMaLoaiNH() {
        return MaLoaiNH;
    }

    public void setMaLoaiNH(String maLoaiNH) {
        MaLoaiNH = maLoaiNH;
    }

    public String getTenLoaiNH() {
        return TenLoaiNH;
    }

    public void setTenLoaiNH(String tenLoaiNH) {
        TenLoaiNH = tenLoaiNH;
    }

    public com.example.eaterydemo.model.NhaHang getNhaHang() {
        return NhaHang;
    }

    public void setNhaHang(com.example.eaterydemo.model.NhaHang nhaHang) {
        NhaHang = nhaHang;
    }
}
