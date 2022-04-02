package com.example.eaterydemo.model;

public class TaiKhoan {
    private String TenTK;
    private String MatKhau;
    private String HoTen;
    private String SDT;
    private String DiaChi;
    private String HinhAnh;
    private String VaiTro;

    public TaiKhoan(String tenTK, String matKhau, String hoTen, String SDT, String diaChi, String hinhAnh, String vaiTro) {
        TenTK = tenTK;
        MatKhau = matKhau;
        HoTen = hoTen;
        this.SDT = SDT;
        DiaChi = diaChi;
        HinhAnh = hinhAnh;
        VaiTro = vaiTro;
    }

    public TaiKhoan() {
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
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

    public String getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(String vaiTro) {
        VaiTro = vaiTro;
    }
}
