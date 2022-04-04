package com.example.eaterydemo.service;

import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.model.NhaHangYeuThich;
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
    @GET("api/getAllnhahangyeuthich")
    Call<ArrayList<NhaHang>> GetAllNhaHangYeuThich();

    @GET("api/nhahangyeuthich/{TenTK}")
    Call<ArrayList<NhaHang>> GetAllNhaHangYeuThichTheoTK(@Path("TenTK") String TenTK);

    @GET("api/nhahangtheomanh/{MaNH}")
    Call<NhaHang> GetNhaHangTheoMaNH(@Path("MaNH") int MaNH);

    @POST("api/insert-nhahangyeuthich")
    Call<Message> ThemNhaHangYeuThich(@Body NhaHangYeuThich nhaHangYeuThich);




    //KHUYẾN MÃI
    @GET("api/khuyenmai")
    Call<ArrayList<KhuyenMai>> GetAllKhuyenMai();


    //MÓN ĂN
    //truyen param thi k có cái đuôi {...} và cái @Query
    @GET("api/monantheonhahang")
    Call<ArrayList<MonAn>> GetAllMonAnTheoNhaHang(@Query("MaNH") int MaNH);

    @GET("api/monantheomama")
    Call<MonAn> GetMonAnTheoMaMA(@Query("MaMA") int MaMA);

    @POST("api/themonanvaogiohang")
    Call<Message> ThemMonAnVaoGioHang(@Query("TenTK") String TenTK,@Query("MaMA") int MaMA,@Query("SL") int SL);

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

    @GET("api/taikhoan")
    Call<TaiKhoan> GetTaiKhoanTheoTenTK(@Query("TenTK") String TenTK);

    @POST("api/capnhatthongtintaikhoan")
    Call<Message> ChinhSuaThongTin(@Query("TenTK") String TenTK, @Query("HoTen") String HoTen, @Query("SDT") String SDT, @Query("DiaChi") String DiaChi);


    //ĐƠN HÀNG

    @GET("api/donhang")
    Call<ArrayList<DonHang>> GetAllDonHang();

    @POST("api/capnhatrangthaidonhang")
    Call<Message> CapNhatTrangThaiDonHang(@Query("MaNH") int MaNH, @Query("TrangThaiDH") int TrangThaiDH);

    @GET("api/getDonHangTheoTK")
    Call<DonHang> GetDonHangTheoTK(@Query("TenTK") String TenTK);

    @GET("api/xoaDonHangTheoTK")
    Call<Message> XoaDonHangCuaTenTKTheoMaDH(@Query("MaDH") String MaDH, @Query("TenTK") String TenTK);

    @GET("api/getAllDonHangTheoTK")
    Call<ArrayList<DonHang>> GetALLDonHang(@Query("TenTK") String TenTK);

    @GET("api/getAllDonHangTheoMaNHTheoTTDH")
    Call<ArrayList<DonHang>> GetAllDonHangTheoMaNHDuaVaoTrangThai(@Query("MaNH") int MaNH,@Query("TrangThaiDH") int TrangThaiDH);

    @POST("api/capnhatrangthaidonhang")
    Call<ArrayList<DonHang>> CapNhat(@Query("MaNH") int MaNH,@Query("TrangThaiDH") int TrangThaiDH);


}
