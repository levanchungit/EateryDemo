package com.example.eaterydemo.model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NhaHang{
    private int DanhGia;
    private String DiaChi;
    private String HinhAnh;
    private LoaiNhaHang LoaiNH;
    private List MAKHUYENMAIs;
    private List MONANs;
    private String MaLoaiNH;
    private int MaNH;
    private String MoTa;
    private TaiKhoan TAIKHOAN;
    private String TenNH;
    private String TenTK;
    private int isYeuThich;

    public NhaHang(int danhGia, String diaChi, String hinhAnh, LoaiNhaHang loaiNH, List MAKHUYENMAIs, List MONANs, String maLoaiNH, int maNH, String moTa, TaiKhoan TAIKHOAN, String tenNH, String tenTK, int isYeuThich) {
        DanhGia = danhGia;
        DiaChi = diaChi;
        HinhAnh = hinhAnh;
        LoaiNH = loaiNH;
        this.MAKHUYENMAIs = MAKHUYENMAIs;
        this.MONANs = MONANs;
        MaLoaiNH = maLoaiNH;
        MaNH = maNH;
        MoTa = moTa;
        this.TAIKHOAN = TAIKHOAN;
        TenNH = tenNH;
        TenTK = tenTK;
        this.isYeuThich = isYeuThich;
    }

    public int getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(int danhGia) {
        DanhGia = danhGia;
    }

    @NotNull
    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(@NotNull String diaChi) {
        DiaChi = diaChi;
    }

    @NotNull
    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(@NotNull String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    @NotNull
    public LoaiNhaHang getLoaiNH() {
        return LoaiNH;
    }

    public void setLoaiNH(@NotNull LoaiNhaHang loaiNH) {
        LoaiNH = loaiNH;
    }

    @NotNull
    public List getMAKHUYENMAIs() {
        return MAKHUYENMAIs;
    }

    public void setMAKHUYENMAIs(@NotNull List MAKHUYENMAIs) {
        this.MAKHUYENMAIs = MAKHUYENMAIs;
    }

    @NotNull
    public List getMONANs() {
        return MONANs;
    }

    public void setMONANs(@NotNull List MONANs) {
        this.MONANs = MONANs;
    }

    @NotNull
    public String getMaLoaiNH() {
        return MaLoaiNH;
    }

    public void setMaLoaiNH(@NotNull String maLoaiNH) {
        MaLoaiNH = maLoaiNH;
    }

    public int getMaNH() {
        return MaNH;
    }

    public void setMaNH(int maNH) {
        MaNH = maNH;
    }

    @NotNull
    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(@NotNull String moTa) {
        MoTa = moTa;
    }

    @NotNull
    public TaiKhoan getTAIKHOAN() {
        return TAIKHOAN;
    }

    public void setTAIKHOAN(@NotNull TaiKhoan TAIKHOAN) {
        this.TAIKHOAN = TAIKHOAN;
    }

    @NotNull
    public String getTenNH() {
        return TenNH;
    }

    public void setTenNH(@NotNull String tenNH) {
        TenNH = tenNH;
    }

    @NotNull
    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(@NotNull String tenTK) {
        TenTK = tenTK;
    }

    public int getIsYeuThich() { return isYeuThich; }

    public void setIsYeuThich(int isYeuThich) { this.isYeuThich = isYeuThich; }
}
