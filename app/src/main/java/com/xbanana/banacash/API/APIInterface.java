package com.xbanana.banacash.API;

import com.xbanana.banacash.DAO.DetailTransaksiDAO;
import com.xbanana.banacash.DAO.LaporanDAO;
import com.xbanana.banacash.DAO.PinDAO;
import com.xbanana.banacash.DAO.ProdukDAO;
import com.xbanana.banacash.DAO.TransactionDAO;
import com.xbanana.banacash.DAO.VoucherDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIInterface {


  @GET("/api/searchPin/{kode_pin}")
  Call<PinDAO> loginRequest(@Path("kode_pin") int kode_pin);

    @POST("api/createPin")
    @FormUrlEncoded
    Call<PinDAO> createProduct (@Field("pin")int pin,
                                @Field("nama_pegawai")String nama_pegawai);

    @GET("api/showProduct/")
    Call<List<ProdukDAO>> showAllProduct();

    @POST("api/createProduct")
    @FormUrlEncoded
    Call<ProdukDAO> createProduct (@Field("nama")String nama,
                                   @Field("stock")int stock,
                                   @Field("harga") int harga);

    @PUT("api/updateProduct/{id}")
    @FormUrlEncoded
    Call<ProdukDAO> updateProduct(@Path("id")int id,
                                  @Field("nama")String nama,
                                  @Field("stock")int stock,
                                  @Field("harga") int harga);

    @DELETE("api/deleteProduct/{id}")
    Call<Void> deleteProduct(@Path("id") String idukuran);

    @GET("api/showTransaction/")
    Call<List<TransactionDAO>> showAllTransaction();

    @POST("api/createTransaction/")
    @FormUrlEncoded
    Call<TransactionDAO> createTransaction (@Field("nama_customer")String nama_customer,
                                            @Field("total_harga")int total_harga,
                                            @Field("tanggal")String tanggal,
                                            @Field("nama_pegawai")String nama_pegawai);

    @PUT("api/updateTransaction/{id}")
    @FormUrlEncoded
    Call<TransactionDAO> updateTransaction(@Path("id") int id,
                                           @Field("nama_customer")String nama_customer,
                                           @Field("total_harga")int total_harga,
                                           @Field("tanggal")String tanggal,
                                           @Field("nama_pegawai")String nama_pegawai);

    @DELETE("api/deleteTransaction/{id}")
    Call<Void> deleteTransaction(@Path("id") int id);

    @GET("api/showDetail/")
    Call<List<DetailTransaksiDAO>> showAllDetail();

    @POST("api/createDetail")
    @FormUrlEncoded
    Call<DetailTransaksiDAO> createVoucher (@Field("id_transaksi")int id_transaksi,
                                            @Field("id_produk")int id_produk,
                                            @Field("jumlah")int jumlah,
                                            @Field("subtotal")int subtotal);

    @PUT("api/updateDetail/{id}")
    @FormUrlEncoded
    Call<DetailTransaksiDAO> updateDetail(@Path("id")int id,
                                          @Field("id_transaksi")int id_transaksi,
                                          @Field("id_produk")int id_produk,
                                          @Field("jumlah")int jumlah,
                                          @Field("subtotal")int subtotal);

    @DELETE("api/deleteDetail/{id}")
    Call<Void> deleteDetail(@Path("id")int id);

    @GET("api/showLaporan/")
    Call<List<LaporanDAO>> showAllLaporan();

    @POST("api/createLaporan")
    @FormUrlEncoded
    Call<LaporanDAO> createLaporan (@Field("nama_produk")String nama_produk,
                                    @Field("jumlah_terjual")int jumlah_terjual,
                                    @Field("total_keuntungan")int total_keuntungan);

    @PUT("api/updateLaporan/{id}")
    @FormUrlEncoded
    Call<LaporanDAO> updateLaporan(@Path("id")int id,
                                  @Field("nama_produk")String nama_produk,
                                  @Field("jumlah_terjual")int jumlah_terjual,
                                  @Field("total_keuntungan")int total_keuntungan);

    @DELETE("api/deleteLaporan/{id}")
    Call<Void> deleteLaporan(@Path("id")int id);

    @GET("api/showVoucher/")
    Call<List<VoucherDAO>> showAllVoucher();

    @POST("api/createVoucher")
    @FormUrlEncoded
    Call<VoucherDAO> createVoucher (@Field("kode")String kode,
                                    @Field("diskon")float diskon);

    @PUT("api/updateVoucher/{id}")
    @FormUrlEncoded
    Call<ProdukDAO> updateVoucher(@Path("id")int id,
                                  @Field("kode")String kode,
                                  @Field("diskon")float diskon);

    @DELETE("api/deleteVoucher/{id}")
    Call<Void> deleteVoucher(@Path("id")int id);

}
