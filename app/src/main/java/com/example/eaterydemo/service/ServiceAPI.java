package com.example.eaterydemo.service;

import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.DonHangChiTiet;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.LoaiNhaHang;
import com.example.eaterydemo.model.Message;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.model.NhaHangYeuThich;
import com.example.eaterydemo.model.TaiKhoan;
import com.example.eaterydemo.model.ThongKe;

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

    @GET("api/GetAllNhaHangChuaXoa")
    Call<ArrayList<NhaHang>> GetAllNhaHangChuaXoa();

    @GET("api/GetAllLoaiNhaHang")
    Call<ArrayList<LoaiNhaHang>> GetAllLoaiNhaHang();

    @GET("api/nhahangtheoloai/{loaiNH}")
    Call<ArrayList<NhaHang>> GetAllNhaHangTheoLoai(@Path("loaiNH") String loaiNH);

    @GET("api/nhahangtheotentk")
    Call<NhaHang> GetNhaHangTheoTenTK(@Query("TenTK") String TenTK);

    @GET("api/getAllnhahangyeuthich")
    Call<ArrayList<NhaHang>> GetAllNhaHangYeuThich();

    @GET("api/GetAllNhaHangYeuThichCuaTaiKhoan")
    Call<ArrayList<NhaHang>> GetAllNhaHangYeuThichCuaTaiKhoan(@Query("TenTK") String TenTK);

    @GET("api/nhahangtheomanh/{MaNH}")
    Call<NhaHang> GetNhaHangTheoMaNH(@Path("MaNH") int MaNH);

    @POST("api/insert-nhahangyeuthich")
    Call<Message> ThemNhaHangYeuThich(@Body NhaHangYeuThich nhaHangYeuThich);

    @POST("api/ChinhSuaThongTinNhaHang")
    Call<Message> ChinhSuaThongTinNhaHang(@Body NhaHang nhaHang);

    @POST("api/XoaNhaHang")
    Call<ArrayList<NhaHang>> XoaNhaHangTheoMaNH(@Query("MaNH") int MaNH);

    @POST("api/ThemNhaHang")
    Call<Message> ThemNhaHang(@Body NhaHang nhaHang);

    @POST("api/ThemHuyNhaHangYeuThich")
    Call<Message> ThemHuyNhaHangYeuThich(@Body NhaHangYeuThich nhaHangYeuThich);

    @GET("api/CheckNhaHangYeuThichCuaTaiKhoan")
    Call<Message> CheckNhaHangYeuThichCuaTaiKhoan(@Query("TenTK") String TenTK, @Query("MaNH") int MaNH);


    //KHUYẾN MÃI
    @GET("api/khuyenmai")
    Call<ArrayList<KhuyenMai>> GetAllKhuyenMai();

    @POST("api/ChinhSuaMaKhuyenMaiTheoNH")
    Call<ArrayList<KhuyenMai>> ChinhSuaMaKhuyenMaiTheoNH(@Body() KhuyenMai khuyenMai);

    //MÓN ĂN
    //truyen param thi k có cái đuôi {...} và cái @Query
    @GET("api/GetAllMonAnTheoNhaHang")
    Call<ArrayList<MonAn>> GetAllMonAnTheoNhaHang(@Query("MaNH") int MaNH);

    @GET("api/monantheomama")
    Call<MonAn> GetMonAnTheoMaMA(@Query("MaMA") int MaMA);

    @POST("api/ThemMonAnVaoGioHang")
    Call<Message> ThemMonAnVaoGioHang(@Query("TenTK") String TenTK, @Query("MaMA") int MaMA, @Query("SL") int SL);


    //TÀI KHOẢN
    @POST("api/dangnhap")
    Call<Message> DangNhap(@Query("TenTK") String TenTK, @Query("MatKhau") String MatKhau);

    @GET("api/GetAllTaiKhoanChuaXoa")
    Call<ArrayList<TaiKhoan>> GetAllTaiKhoanChuaXoa();

    @POST("api/dangky")
    Call<Message> DangKy(@Body TaiKhoan taiKhoan);

    @GET("api/taikhoan")
    Call<ArrayList<TaiKhoan>> GetAllTaiKhoan();

    @POST("api/capnhatthongtintaikhoan")
    Call<Message> CapNhatThongTinTaiKhoan(@Body TaiKhoan taiKhoan);

    @GET("api/getCodeResetPassword")
    Call<Message> SendEmailForgotPassword(@Query("TenTK") String TenTK);

    @GET("api/resetPassword")
    Call<Message> CapNhatMatKhau(@Query("TenTK") String TenTK, @Query("Code") String code, @Query("matKhauMoi") String matKhauMoi);

    @GET("api/taikhoan")
    Call<TaiKhoan> GetTaiKhoanTheoTenTK(@Query("TenTK") String TenTK);

    @POST("api/capnhatthongtintaikhoan")
    Call<Message> ChinhSuaThongTin(@Query("TenTK") String TenTK, @Query("HoTen") String HoTen, @Query("SDT") String SDT, @Query("DiaChi") String DiaChi);

    @POST("api/XoaTaiKhoan")
    Call<ArrayList<TaiKhoan>> XoaTaiKhoanTheoTenTK(@Query("TenTK") String TenTK);

    @POST("api/CapNhatMatKhauCuaTK")
    Call<Message> CapNhatMatKhauCuaTK(@Query("TenTK") String TenTK, @Query("MatKhauCu") String MatKhauCu, @Query("MatKhauMoi") String MatKhauMoi, @Query("NhapLaiMatKhauMoi") String NhapLaiMatKhauMoi);

    @POST("api/XoaDonHangKhiDatMonAnKhacNhaHang")
    Call<Message> XoaDonHangKhiDatMonAnKhacNhaHang(@Query("TenTK") String TenTK);


    //ĐƠN HÀNG

    @GET("api/donhang")
    Call<ArrayList<DonHang>> GetAllDonHang();

    @POST("api/capNhatTrangThaiDHCuaChuCuaHang")
    Call<Message> CapNhatTrangThaiDonHang(@Query("MaDH") int MaNH, @Query("TrangThaiDH") int TrangThaiDH);

    @POST("api/CapNhatTrangThaiDonHangCuaTK")
    Call<Message> CapNhatTrangThaiDonHangCuaTK(@Body DonHang donHang);

    @GET("api/getDonHangTheoTK")
    Call<DonHang> GetDonHangTheoTK(@Query("TenTK") String TenTK);

    @GET("api/xoaDonHangTheoTK")
    Call<Message> XoaDonHangCuaTenTKTheoMaDH(@Query("MaDH") String MaDH, @Query("TenTK") String TenTK);

    @GET("api/getAllDonHangTheoTK")
    Call<ArrayList<DonHang>> GetALLDonHang(@Query("TenTK") String TenTK);

    @GET("api/getAllDonHangTheoMaNHTheoTTDH")
    Call<ArrayList<DonHang>> GetAllDonHangTheoMaNHDuaVaoTrangThai(@Query("MaNH") int MaNH, @Query("TrangThaiDH") int TrangThaiDH);

    @POST("api/capnhatrangthaidonhang")
    Call<ArrayList<DonHang>> CapNhat(@Query("MaNH") int MaNH, @Query("TrangThaiDH") int TrangThaiDH);

    @POST("api/XoaMonAnTrongDonHang")
    Call<ArrayList<DonHangChiTiet>> XoaMonAnTrongDonHang(@Query("MaDHCT") int MaDHCT, @Query("MaMA") int MaMA);

    @GET("api/GetTongTienCuaDonHang")
    Call<Double> GetTongTienCuaDonHang(@Query("MaDH") int MaDH);

    @POST("api/CapNhatDiaChiGiaoHang")
    Call<String> CapNhatDiaChiGiaoHang(@Query("MaDH") int MaDH, @Query("DiaChiMoi") String DiaChiMoi);


    // Thống kê
    @GET("api/GetTongDoanhThuDonHangTheoNH")
    Call<Double> GetTongDoanhThuDonHangTheoNH(@Query("MaNH") int MaNH, @Query("TuNgay") String TuNgay, @Query("DenNgay") String DenNgay);

    @GET("api/GetTongDoanhThuCuaTungMonAnTheoNH ")
    Call<ArrayList<ThongKe>> GetTongDoanhThuCuaTungMonAnTheoNH(@Query("MaNH") int MaNH);


    //    Cập nhật số lượng món ăn trong giỏ hàng
    @POST("api/CapNhatSoLuongTangGiamMonAn ")
    Call<Double> CapNhatSoLuongTangGiamMonAn(@Query("MaDHCT") int MaDHCT, @Query("MaMA") int MaMA, @Query("tanghoacgiam") String tanghoacgiam);
}
