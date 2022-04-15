package com.example.eaterydemo.service;

import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.MonAn;
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
    @GET("api/getAllnhahangyeuthich")
    Call<ArrayList<NhaHang>> GetAllNhaHangYeuThich();

    @GET("api/nhahangyeuthich/{TenTK}")
    Call<ArrayList<NhaHang>> GetAllNhaHangYeuThichTheoTK(@Path("TenTK") String TenTK);

    @GET("api/nhahangtheomanh/{MaNH}")
    Call<NhaHang> GetNhaHangTheoMaNH(@Path("MaNH") int MaNH);



    //KHUYẾN MÃI
    @GET("api/khuyenmai")
    Call<ArrayList<KhuyenMai>> GetAllKhuyenMai();

    @GET("api/GetAllMaKhuyenMaiTheoNH")
    Call<ArrayList<KhuyenMai>> GetAllMaKhuyenMaiTheoNH(@Query("MaNH") int MaNH);

    @POST("api/ThemMaKhuyenMai")
    Call<ArrayList<KhuyenMai>> ThemMaKhuyenMai(@Body KhuyenMai khuyenMai) ;

    @POST("api/ChinhSuaMaKhuyenMaiTheoNH")
    Call<ArrayList<KhuyenMai>> ChinhSuaMaKhuyenMaiTheoNH(@Body KhuyenMai khuyenMai) ;

    @POST("api/XoaMaKhuyenMai")
    Call<ArrayList<KhuyenMai>> XoaMaKhuyenMai(@Query("MaKM") String MaKM);
    //MÓN ĂN
    //truyen param thi k có cái đuôi {...} và cái @Query
//    @GET("api/monantheonhahang")
//    Call<ArrayList<MonAn>> GetAllMonAnTheoNhaHang(@Query("MaNH") int MaNH);

    @GET("api/GetAllMonAnTheoNhaHang")
    Call<ArrayList<MonAn>> GetAllMonAnTheoNhaHang(@Query("MaNH") int MaNH);

    @POST("api/ThemMonAnTrongNhaHang")
    Call<ArrayList<MonAn>> ThemMonAnTrongNhaHang(@Body MonAn monAn) ;

    @POST("api/ChinhSuaMonAnTrongNhaHang")
    Call<ArrayList<MonAn>> ChinhSuaMonAnTrongNhaHang(@Body MonAn monAn) ;

    @GET("api/monantheomama")
    Call<MonAn> GetMonAnTheoMaMA(@Query("MaMA") int MaMA);

    @GET("api/themonanvaogiohang")
    Call<Message> ThemMonAnVaoGioHang(@Query("TenTK") String TenTK,@Query("MaMA") int MaMA,@Query("SL") int SL);

    @POST("api/XoaMonAn")
    Call<ArrayList<MonAn>> XoaMonAnTrongNhaHang(@Query("MaMA") int MaMA);

    //TÀI KHOẢN
    @POST("api/dangnhap")
    Call<Message> DangNhap(@Query("TenTK") String TenTK, @Query("MatKhau") String MatKhau);

    @POST("api/dangky")
    Call<Message> DangKy(@Body TaiKhoan taiKhoan);

    @GET("api/taikhoan")
    Call<ArrayList<TaiKhoan>> GetAllTaiKhoan();

    @GET("api/taikhoan/{TenTK}")
    Call<TaiKhoan> GetTaiKhoanTheoTenTK(@Path("TenTK") String TenTK);

    //truyền k có đuôi api/.../{}, k có dấu {} này thì phải là @query
    //thì check api trên postman thì phải check thế này
    @GET("api/doimatkhau")
    Call<Message> DoiMatKhau(@Query("TenTK") String TenTK, @Query("MatKhauCu") String MatKhauCu, @Query("MatKhauMoi") String MatKhauMoi);

    @POST("api/capnhatthongtintaikhoan")
    Call<Message> ChinhSuaThongTin(@Query("TenTK") String TenTK, @Query("HoTen") String HoTen, @Query("SDT") String SDT, @Query("DiaChi") String DiaChi);

    @GET("api/getCodeResetPassword")
    Call<Message> SendEmailForgotPassword(@Query("TenTK") String TenTK);

    @GET("api/resetPassword")
    Call<Message> CapNhatMatKhau(@Query("TenTK") String TenTK, @Query("Code") String code, @Query("matKhauMoi")String matKhauMoi);

    @POST("api/ChinhSuaThongTinNhaHang")
    Call<Message> ChinhSuaThongTinNhaHang(@Body NhaHang nhaHang);


    //ĐƠN HÀNG

    @GET("api/donhang")
    Call<ArrayList<DonHang>> GetAllDonHang();

    @GET("api/LichSuNhungDonHangTrong1NgayCuaNH")
    Call<ArrayList<DonHang>> LichSuNhungDonHangTrong1NgayCuaNH(@Query("MaNH")int MaNH,@Query("NgayMua") String NgayMua);

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

}
