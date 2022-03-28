package com.example.eaterydemo.model;

public class NhaHangYeuThich {
    private String TenTK;
    private int MaNH;

    public NhaHangYeuThich(String tenTK, int maNH) {
        TenTK = tenTK;
        MaNH = maNH;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }

    public int getMaNH() {
        return MaNH;
    }

    public void setMaNH(int maNH) {
        MaNH = maNH;
    }
}
