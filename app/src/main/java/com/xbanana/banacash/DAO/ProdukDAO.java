package com.xbanana.banacash.DAO;

import com.google.gson.annotations.SerializedName;

public class ProdukDAO {
    @SerializedName("id")
    int id;
    @SerializedName("nama")
    String nama;
    @SerializedName("stock")
    int stock;
    @SerializedName("harga")
    int harga;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProdukDAO(int id, String nama, int stock, int harga) {
        this.id = id;
        this.nama = nama;
        this.stock = stock;
        this.harga = harga;
    }
}
