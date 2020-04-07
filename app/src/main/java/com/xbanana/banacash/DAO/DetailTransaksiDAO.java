package com.xbanana.banacash.DAO;

import com.google.gson.annotations.SerializedName;

public class DetailTransaksiDAO {
    @SerializedName("id_transaksi")
    int id_transaksi;
    @SerializedName("id_produk")
    int id_produk;
    @SerializedName("jumlah_terjual")
    int jumlah_terjual;
    @SerializedName("subtotal")
    int subtotal;

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
        return jumlah_terjual;
    }

    public void setJumlah(int jumlah_terjual) {
        this.jumlah_terjual = jumlah_terjual;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public DetailTransaksiDAO(int id_transaksi, int id_produk, int jumlah_terjual, int subtotal) {
        this.id_transaksi = id_transaksi;
        this.id_produk = id_produk;
        this.jumlah_terjual = jumlah_terjual;
        this.subtotal = subtotal;
    }
}
