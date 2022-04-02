package com.example.eaterydemo.service;

import android.graphics.ColorSpace;

import com.bumptech.glide.load.model.Model;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.model.TaiKhoan;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceAPI {
    String BASE_Service = "https://eatery.congtydacap.club/";

    //áp dụng thực tế
    //NHÀ HÀNG
    @GET("api/nhahang")
    Call<ArrayList<NhaHang>> GetAllNhaHang();

    @GET("api/nhahangtheoloai/{loaiNH}")
    Call<ArrayList<NhaHang>> GetAllNhaHangTheoLoai(@Path("loaiNH") String loaiNH);

    @GET("api/nhahangtheotentk")
    Call<NhaHang> GetNhaHangTheoTenTK(@Query("TenTK") String TenTK);


    //KHUYẾN MÃI
    @GET("api/khuyenmai")
    Call<ArrayList<KhuyenMai>> GetAllKhuyenMai();


    //TÀI KHOẢN
    @POST("api/dangnhap")
    Call<Message> DangNhap(@Query("TenTK") String TenTK, @Query("MatKhau") String MatKhau);

    @POST("api/dangky")
    Call<Message> DangKy(@Body TaiKhoan taiKhoan);

    @GET("api/taikhoan")
    Call<ArrayList<TaiKhoan>> GetAllTaiKhoan();

    @POST("api/capnhatthongtintaikhoan")
    Call<Message> CapNhatThongTinTaiKhoan(@Body TaiKhoan taiKhoan);

    @GET("api/getCodeResetPassword")
    Call<Message> SendEmailForgotPassword(@Query("TenTK") String TenTK);

    @GET("api/resetPassword")
    Call<Message> CapNhatMatKhau(@Query("TenTK") String TenTK, @Query("Code") String code, @Query("matKhauMoi")String matKhauMoi);

    //ĐƠN HÀNG

    @GET("api/donhang")
    Call<ArrayList<DonHang>> GetAllDonHang();

    @POST("api/capnhatrangthaidonhang")
    Call<Message> CapNhatTrangThaiDonHang(@Query("MaNH") int MaNH, @Query("TrangThaiDH") int TrangThaiDH);

    @GET("api/xoaDonHangTheoTK")
    Call<Message> XoaDonHangCuaTenTKTheoMaDH(@Query("MaDH") String MaDH, @Query("TenTK") String TenTK);

    @GET("api/getAllDonHangTheoTK")
    Call<ArrayList<DonHang>> GetALLDonHang(@Query("TenTK") String TenTK);

    @GET("api/getAllDonHangTheoMaNHTheoTTDH")
    Call<ArrayList<DonHang>> GetAllDonHangTheoMaNHDuaVaoTrangThai(@Query("MaNH") int MaNH,@Query("TrangThaiDH") int TrangThaiDH);

}
