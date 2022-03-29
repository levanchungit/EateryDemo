package com.example.eaterydemo.model;

import java.util.List;

public class DonHang {
    private int MaDonHang;
    private String DiaChi;
    private int TrangThaiDH;
    private float TongTien;
    private String NgayMua;
    private String TenTK;
    private String nameRes;
    private int countSL;
    private List<DonHangChiTiet> DONHANGCHITIETs;

    public DonHang(int maDonHang, String diaChi, int trangThaiDH, float tongTien, String ngayMua, String tenTK, String nameRes, int countSL, List<DonHangChiTiet> DONHANGCHITIETs) {
        MaDonHang = maDonHang;
        DiaChi = diaChi;
        TrangThaiDH = trangThaiDH;
        TongTien = tongTien;
        NgayMua = ngayMua;
        TenTK = tenTK;
        this.nameRes = nameRes;
        this.countSL = countSL;
        this.DONHANGCHITIETs = DONHANGCHITIETs;
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

    public String getNameRes() {
        return nameRes;
    }

    public void setNameRes(String nameRes) {
        this.nameRes = nameRes;
    }

    public int getCountSL() {
        return countSL;
    }

    public void setCountSL(int countSL) {
        this.countSL = countSL;
    }

    public List<DonHangChiTiet> getDONHANGCHITIETs() {
        return DONHANGCHITIETs;
    }

    public void setDONHANGCHITIETs(List<DonHangChiTiet> DONHANGCHITIETs) {
        this.DONHANGCHITIETs = DONHANGCHITIETs;
    }
}
