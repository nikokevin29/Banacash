package com.xbanana.banacash.DAO;

import com.google.gson.annotations.SerializedName;

public class ProdukDAO {
    @SerializedName("id")
    int id;
    @SerializedName("nama")
    String nama;
    @SerializedName("stok")
    int stok;
    @SerializedName("harga")
    int harga;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getStock() {
        return stok;
    }

    public void setStock(int stok) {
        this.stok = stok;
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

    public ProdukDAO(int id, String nama, int stok, int harga) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }
}
