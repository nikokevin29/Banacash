package com.xbanana.banacash.DAO;

import com.google.gson.annotations.SerializedName;

public class DetailTransaksiDAO {
    @SerializedName("id_transaksi")
    int id_transaksi;
    @SerializedName("id_produk")
    String id_produk;
    @SerializedName("jumlah_terjual")
    int jumlah_terjual;
    @SerializedName("subtotal")
    Double subtotal;

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public int getJumlah() {
        return jumlah_terjual;
    }

    public void setJumlah(int jumlah_terjual) {
        this.jumlah_terjual = jumlah_terjual;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public DetailTransaksiDAO(int id_transaksi, String id_produk, int jumlah_terjual, Double subtotal) {
        this.id_transaksi = id_transaksi;
        this.id_produk = id_produk;
        this.jumlah_terjual = jumlah_terjual;
        this.subtotal = subtotal;
    }
}
