package com.example.eaterydemo.model;

public class NhaHang{
    private int MaNH;
    private String TenNH;
    private String DiaChi;
    private String HinhAnh;
    private String MoTa;
    private float DanhGia;
    private String TenTK;
    private String MaLoaiNH;
    private String TenLoaiNH;
    private String HoTen;
    private float IsYeuThich;

    public NhaHang(int maNH, String tenNH, String diaChi, String moTa) {
        MaNH = maNH;
        TenNH = tenNH;
        DiaChi = diaChi;
        MoTa = moTa;
    }

    public NhaHang(int maNH, String tenNH, String diaChi, String hinhAnh, String moTa, float danhGia, String tenTK, String maLoaiNH, String tenLoaiNH, String hoTen, float isYeuThich) {
        MaNH = maNH;
        TenNH = tenNH;
        DiaChi = diaChi;
        HinhAnh = hinhAnh;
        MoTa = moTa;
        DanhGia = danhGia;
        TenTK = tenTK;
        MaLoaiNH = maLoaiNH;
        TenLoaiNH = tenLoaiNH;
        HoTen = hoTen;
        IsYeuThich = isYeuThich;
    }

    public int getMaNH() {
        return MaNH;
    }

    public void setMaNH(int maNH) {
        MaNH = maNH;
    }

    public String getTenNH() {
        return TenNH;
    }

    public void setTenNH(String tenNH) {
        TenNH = tenNH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public float getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(float danhGia) {
        DanhGia = danhGia;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }

    public String getMaLoaiNH() {
        return MaLoaiNH;
    }

    public void setMaLoaiNH(String maLoaiNH) {
        MaLoaiNH = maLoaiNH;
    }

    public String getTenLoaiNH() {
        return TenLoaiNH;
    }

    public void setTenLoaiNH(String tenLoaiNH) {
        TenLoaiNH = tenLoaiNH;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public float getIsYeuThich() {
        return IsYeuThich;
    }

    public void setIsYeuThich(float isYeuThich) {
        IsYeuThich = isYeuThich;
    }
}
