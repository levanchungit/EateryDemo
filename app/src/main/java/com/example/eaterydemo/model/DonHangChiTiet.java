package com.example.eaterydemo.model;

public class DonHangChiTiet {
    private int MaDHCT;
    private int MaMA;
    private int SL;
    private float DonGia;

    public DonHangChiTiet(int maDHCT, int maMA, int SL, float donGia) {
        MaDHCT = maDHCT;
        MaMA = maMA;
        this.SL = SL;
        DonGia = donGia;
    }

    public int getMaDHCT() {
        return MaDHCT;
    }

    public void setMaDHCT(int maDHCT) {
        MaDHCT = maDHCT;
    }

    public int getMaMA() {
        return MaMA;
    }

    public void setMaMA(int maMA) {
        MaMA = maMA;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float donGia) {
        DonGia = donGia;
    }
}
