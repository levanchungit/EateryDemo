package com.example.eaterydemo.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KhuyenMai {
    private String MaKM;
    private int MaNH;
    private NhaHang NHAHANG;
    private int SL;
    private String TenKhuyenMai;
    private int TienKM;
    private String HinhAnh;

    public KhuyenMai(String maKM, int maNH, NhaHang NHAHANG, int SL, String TenKhuyenMai, int tienKM, String HinhAnh) {
        MaKM = maKM;
        MaNH = maNH;
        this.NHAHANG = NHAHANG;
        this.SL = SL;
        TenKhuyenMai = TenKhuyenMai;
        TienKM = tienKM;
        this.HinhAnh = HinhAnh;
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

    public String getTenMaKhuyenMai() {
        return TenKhuyenMai;
    }

    public void setTenMaKhuyenMai(String tenMaKhuyenMai) {
        TenKhuyenMai = tenMaKhuyenMai;
    }

    public int getTienKM() {
        return TienKM;
    }

    public void setTienKM(int tienKM) {
        TienKM = tienKM;
    }
}
