package com.example.eaterydemo.model;

public class DonHangChiTiet {
    private String TenMA;
    private float giaMA;
    private String MONAN = null;
    private float MaDHCT;
    private float MaMA;
    private float SL;
    private float DonGia;
    private String HinhAnhMA;

    public DonHangChiTiet() {
    }

    public DonHangChiTiet(String tenMA, float giaMA, String MONAN, float maDHCT, float maMA, float SL, float donGia, String hinhAnhMA) {
        TenMA = tenMA;
        this.giaMA = giaMA;
        this.MONAN = MONAN;
        MaDHCT = maDHCT;
        MaMA = maMA;
        this.SL = SL;
        DonGia = donGia;
        HinhAnhMA = hinhAnhMA;
    }

    public String getTenMA() {
        return TenMA;
    }

    public void setTenMA(String tenMA) {
        TenMA = tenMA;
    }

    public float getGiaMA() {
        return giaMA;
    }

    public void setGiaMA(float giaMA) {
        this.giaMA = giaMA;
    }

    public String getMONAN() {
        return MONAN;
    }

    public void setMONAN(String MONAN) {
        this.MONAN = MONAN;
    }

    public float getMaDHCT() {
        return MaDHCT;
    }

    public void setMaDHCT(float maDHCT) {
        MaDHCT = maDHCT;
    }

    public float getMaMA() {
        return MaMA;
    }

    public void setMaMA(float maMA) {
        MaMA = maMA;
    }

    public float getSL() {
        return SL;
    }

    public void setSL(float SL) {
        this.SL = SL;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float donGia) {
        DonGia = donGia;
    }

    public String getHinhAnh() {
        return HinhAnhMA;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnhMA = hinhAnh;
    }
}
