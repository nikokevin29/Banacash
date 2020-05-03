package com.xbanana.banacash.DAO;

import com.google.gson.annotations.SerializedName;

public class TransactionDAO {
    @SerializedName("id")
    int id;
    @SerializedName("id_transaksi")
    int id_transaksi;
    @SerializedName("nama_customer")
    String nama_customer;
    @SerializedName("tanggal")
    String tangal;
    @SerializedName("nama_pegawai")
    String nama_pegawai;
    @SerializedName("total_harga")
    int total_harga;


    public String getNama_customer() {
        return nama_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
    }

    public String getTangal() {
        return tangal;
    }

    public void setTangal(String tangal) {
        this.tangal = tangal;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public int getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransactionDAO(int id, int id_transaksi, String nama_customer, String tangal, String nama_pegawai, int total_harga) {
        this.id = id;
        this.id_transaksi = id_transaksi;
        this.nama_customer = nama_customer;
        this.tangal = tangal;
        this.nama_pegawai = nama_pegawai;
        this.total_harga = total_harga;
    }
}
