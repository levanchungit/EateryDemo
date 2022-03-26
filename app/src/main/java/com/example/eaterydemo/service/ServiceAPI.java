package com.example.eaterydemo.service;

import com.example.eaterydemo.model.DangNhapModel;
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

public interface ServiceAPI {
    String BASE_Service = "https://eatery.congtydacap.club/";

    //áp dụng thực tế
    //NHÀ HÀNG
    @GET("api/nhahang")
    Call<ArrayList<NhaHang>> GetAllNhaHang();

    @GET("api/nhahangtheoloai/{loaiNH}")
    Call<ArrayList<NhaHang>> GetAllNhaHangTheoLoai(@Path("loaiNH") String loaiNH);

    //KHUYẾN MÃI
    @GET("api/khuyenmai")
    Call<ArrayList<KhuyenMai>> GetAllKhuyenMai();

    //TÀI KHOẢN
    @GET("api/taikhoan")
    Call<ArrayList<TaiKhoan>> GetAllTaiKhoan();

    @POST("api/insert-taikhoan")
    Call<Message> AddTaiKhoan(@Body TaiKhoan taiKhoan);

    @POST("api/dangnhap")
    Call<Message> DangNhap(@Body DangNhapModel dangNhapModel);



//    @GET("api/product-detail")
//    Observable<Product> GetDetailProduct(@Query("id") int id);
//
//    @POST("api/add-product")
//    Observable<Message> AddProduct(@Body Product product);
}
