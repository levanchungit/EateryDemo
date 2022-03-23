package com.example.eaterydemo.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KhuyenMai {
    private String MaKM;
    private int MaNH;
    private NhaHang NHAHANG;
    private int SL;
    private String TenMaKhuyenMai;
    private int TienKM;

    public KhuyenMai(String maKM, int maNH, NhaHang NHAHANG, int SL, String tenMaKhuyenMai, int tienKM) {
        MaKM = maKM;
        MaNH = maNH;
        this.NHAHANG = NHAHANG;
        this.SL = SL;
        TenMaKhuyenMai = tenMaKhuyenMai;
        TienKM = tienKM;
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

    public String getTenMaKhuyenMai() {
        return TenMaKhuyenMai;
    }

    public void setTenMaKhuyenMai(String tenMaKhuyenMai) {
        TenMaKhuyenMai = tenMaKhuyenMai;
    }

    public int getTienKM() {
        return TienKM;
    }

    public void setTienKM(int tienKM) {
        TienKM = tienKM;
    }
}
