package com.example.eaterydemo.service;

import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.NhaHang;
import com.example.eaterydemo.model.TaiKhoan;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceAPI {
    String BASE_Service = "https://eatery.congtydacap.club/";

    //áp dụng thực tế
    @GET("api/nhahang")
    Observable<ArrayList<NhaHang>> GetAllNhaHang();

    @GET("api/makhuyenmai")
    Observable<ArrayList<KhuyenMai>> GetAllMaKhuyenMai();

    @GET("api/taikhoan")
    Observable<ArrayList<TaiKhoan>> GetAllTaiKhoan();


//    @GET("api/product-detail")
//    Observable<Product> GetDetailProduct(@Query("id") int id);
//
//    @POST("api/add-product")
//    Observable<Message> AddProduct(@Body Product product);
}
