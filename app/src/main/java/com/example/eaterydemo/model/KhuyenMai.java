package com.example.eaterydemo.model;

public class KhuyenMai {
    private int maKhuyenMai;
    private String tenMaKhuyenMai;

    public KhuyenMai(int maKhuyenMai, String tenMaKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenMaKhuyenMai = tenMaKhuyenMai;
    }

    public int getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(int maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenMaKhuyenMai() {
        return tenMaKhuyenMai;
    }

    public void setTenMaKhuyenMai(String tenMaKhuyenMai) {
        this.tenMaKhuyenMai = tenMaKhuyenMai;
    }
}
