package com.xbanana.banacash.DAO;

import com.google.gson.annotations.SerializedName;

public class DetailTransaksiDAO {
    @SerializedName("id")
    int id;
    @SerializedName("id_transaksi")
    int id_transaksi;
    @SerializedName("id_produk")
    int id_produk;
    @SerializedName("jumlah")
    int jumlah;
    @SerializedName("subtotal")
    Double subtotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public DetailTransaksiDAO(int id, int id_transaksi, int id_produk, int jumlah, Double subtotal) {
        this.id = id;
        this.id_transaksi = id_transaksi;
        this.id_produk = id_produk;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }
}
