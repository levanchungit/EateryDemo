package com.example.eaterydemo.model;

public class DonHang {
    private int MaDonHang;
    private String DiaChi;
    private int TrangThaiDH;
    private float TongTien;
    private String NgayMua;
    private String TenTK;

    public DonHang(int maDonHang, String diaChi, int trangThaiDH, float tongTien, String ngayMua, String tenTK) {
        MaDonHang = maDonHang;
        DiaChi = diaChi;
        TrangThaiDH = trangThaiDH;
        TongTien = tongTien;
        NgayMua = ngayMua;
        TenTK = tenTK;
    }

    public int getMaDonHang() {
        return MaDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        MaDonHang = maDonHang;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public int getTrangThaiDH() {
        return TrangThaiDH;
    }

    public void setTrangThaiDH(int trangThaiDH) {
        TrangThaiDH = trangThaiDH;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setTongTien(float tongTien) {
        TongTien = tongTien;
    }

    public String getNgayMua() {
        return NgayMua;
    }

    public void setNgayMua(String ngayMua) {
        NgayMua = ngayMua;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }
}
