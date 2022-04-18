package com.example.eaterydemo.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KhuyenMai {
    private NhaHang NHAHANG;
    private String MaKM;
    private String TenKhuyenMai;
    private int TienKM;
    private int SL;
    private int MaNH;
    private String HinhAnh;

    public KhuyenMai(NhaHang NHAHANG, String maKM, String tenKhuyenMai, int tienKM, int SL, int maNH, String hinhAnh) {
        this.NHAHANG = NHAHANG;
        MaKM = maKM;
        TenKhuyenMai = tenKhuyenMai;
        TienKM = tienKM;
        this.SL = SL;
        MaNH = maNH;
        HinhAnh = hinhAnh;
    }

    public KhuyenMai(String maKM, String tenKhuyenMai, int tienKM, int SL, int maNH, String hinhAnh) {
        MaKM = maKM;
        TenKhuyenMai = tenKhuyenMai;
        TienKM = tienKM;
        this.SL = SL;
        MaNH = maNH;
        HinhAnh = hinhAnh;
    }

    public KhuyenMai(String maKM, String tenKhuyenMai, int tienKM, int SL, int maNH) {
        MaKM = maKM;
        TenKhuyenMai = tenKhuyenMai;
        TienKM = tienKM;
        this.SL = SL;
        MaNH = maNH;
    }

    public KhuyenMai() {
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String maKM) {
        MaKM = maKM;
    }

    public int getMaNH() {
        return MaNH;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public void setMaNH(int maNH) {
        MaNH = maNH;
    }

    public NhaHang getNHAHANG() {
        return NHAHANG;
    }

    public void setNHAHANG(NhaHang NHAHANG) {
        this.NHAHANG = NHAHANG;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public String getTenKhuyenMai() {
        return TenKhuyenMai;
    }

    public void setTenMaKhuyenMai(String TenKhuyenMai) {
        TenKhuyenMai = TenKhuyenMai;
    }

    public int getTienKM() {
        return TienKM;
    }

    public void setTienKM(int tienKM) {
        TienKM = tienKM;
    }
}
