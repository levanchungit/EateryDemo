package com.example.eaterydemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DonHang implements Parcelable {
    private int MaDonHang;
    private String DiaChi;
    private int TrangThaiDH;
    private float TongTien;
    private String NgayMua;
    private String TenTK;
    private String nameRes;
    private int countSL;
    private List<DonHangChiTiet> DONHANGCHITIETs;
    private TaiKhoan TAIKHOAN;
    private int MaNH;

    public DonHang() {
    }


    public DonHang(int maDonHang, String diaChi, int trangThaiDH, float tongTien, String tenTK) {
        MaDonHang = maDonHang;
        DiaChi = diaChi;
        TrangThaiDH = trangThaiDH;
        TongTien = tongTien;
        TenTK = tenTK;
    }

    public DonHang(int maDonHang, String diaChi, int trangThaiDH, float tongTien, String ngayMua, String tenTK, String nameRes, int countSL, List<DonHangChiTiet> DONHANGCHITIETs, TaiKhoan TAIKHOAN) {
        MaDonHang = maDonHang;
        DiaChi = diaChi;
        TrangThaiDH = trangThaiDH;
        TongTien = tongTien;
        NgayMua = ngayMua;
        TenTK = tenTK;
        this.nameRes = nameRes;
        this.countSL = countSL;
        this.DONHANGCHITIETs = DONHANGCHITIETs;
        this.TAIKHOAN = TAIKHOAN;
    }

    public DonHang(int maDonHang, String diaChi, int trangThaiDH, float tongTien, String ngayMua, String tenTK, String nameRes, int countSL, List<DonHangChiTiet> DONHANGCHITIETs, TaiKhoan TAIKHOAN, int maNH) {
        MaDonHang = maDonHang;
        DiaChi = diaChi;
        TrangThaiDH = trangThaiDH;
        TongTien = tongTien;
        NgayMua = ngayMua;
        TenTK = tenTK;
        this.nameRes = nameRes;
        this.countSL = countSL;
        this.DONHANGCHITIETs = DONHANGCHITIETs;
        this.TAIKHOAN = TAIKHOAN;
        MaNH = maNH;
    }

    protected DonHang(Parcel in) {
        MaDonHang = in.readInt();
        DiaChi = in.readString();
        TrangThaiDH = in.readInt();
        TongTien = in.readFloat();
        NgayMua = in.readString();
        TenTK = in.readString();
        nameRes = in.readString();
        countSL = in.readInt();
        DONHANGCHITIETs = DONHANGCHITIETs;
    }

    public static final Parcelable.Creator<DonHang> CREATOR = new Parcelable.Creator<DonHang>() {
        @Override
        public DonHang createFromParcel(Parcel in) {
            return new DonHang(in);
        }

        @Override
        public DonHang[] newArray(int size) {
            return new DonHang[size];
        }
    };

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

    public int getMaNH() {
        return MaNH;
    }

    public void setMaNH(int maNH) {
        MaNH = maNH;
    }

    public TaiKhoan getTAIKHOAN() {
        return TAIKHOAN;
    }

    public void setTAIKHOAN(TaiKhoan TAIKHOAN) {
        this.TAIKHOAN = TAIKHOAN;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(MaDonHang);
        parcel.writeString(DiaChi);
        parcel.writeInt(TrangThaiDH);
        parcel.writeFloat(TongTien);
        parcel.writeString(NgayMua);
        parcel.writeString(TenTK);
        parcel.writeString(nameRes);
        parcel.writeInt(countSL);
    }
}
