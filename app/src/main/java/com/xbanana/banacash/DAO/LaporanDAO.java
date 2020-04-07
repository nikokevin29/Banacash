package com.xbanana.banacash.DAO;

import com.google.gson.annotations.SerializedName;

public class LaporanDAO {
    @SerializedName("id")
    int id;
    @SerializedName("nama_produk")
    String nama_produk;
    @SerializedName("jumlah_terjual")
    int jumlah_terjual;
    @SerializedName("total_keuntungan")
    int total_keuntungan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public int getJumlah_terjual() {
        return jumlah_terjual;
    }

    public void setJumlah_terjual(int jumlah_terjual) {
        this.jumlah_terjual = jumlah_terjual;
    }

    public int getTotal_keuntungan() {
        return total_keuntungan;
    }

    public void setTotal_keuntungan(int total_keuntungan) {
        this.total_keuntungan = total_keuntungan;
    }

    public LaporanDAO(int id, String nama_produk, int jumlah_terjual, int total_keuntungan) {
        this.id = id;
        this.nama_produk = nama_produk;
        this.jumlah_terjual = jumlah_terjual;
        this.total_keuntungan = total_keuntungan;
    }
}
