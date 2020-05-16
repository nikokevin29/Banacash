package com.xbanana.banacash.DAO;

import com.google.gson.annotations.SerializedName;

public class TransactionDAO {
    @SerializedName("id_transaksi")
    int id_transaksi;
    @SerializedName("nama_customer")
    String nama_customer;
    @SerializedName("tanggal")
    String tanggal;
    @SerializedName("nama_pegawai")
    String nama_pegawai;
    @SerializedName("total_harga")
    double total_harga;


    public String getNama_customer() {
        return nama_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
    }

    public String getTangal() {
        return tanggal;
    }

    public void setTangal(String tangal) {
        this.tanggal = tangal;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public double getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(double total_harga) {
        this.total_harga = total_harga;
    }

    public int getId() {
        return id_transaksi;
    }

    public void setId(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public TransactionDAO(int id_transaksi, String nama_customer, String tanggal, String nama_pegawai, double total_harga) {
        this.id_transaksi = id_transaksi;
        this.nama_customer = nama_customer;
        this.tanggal = tanggal;
        this.nama_pegawai = nama_pegawai;
        this.total_harga = total_harga;
    }
}
