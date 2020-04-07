package com.xbanana.banacash.DAO;

import com.google.gson.annotations.SerializedName;

public class PinDAO {

    @SerializedName("kode_pin")
    int kode_pin;
    @SerializedName("nama_pegawai")
    String nama_pegawai;

    public int getKode_pin() {
        return kode_pin;
    }

    public void setKode_pin(int kode_pin) {
        this.kode_pin = kode_pin;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public PinDAO(int id, int kode_pin, String nama_pegawai) {

        this.kode_pin = kode_pin;
        this.nama_pegawai = nama_pegawai;
    }
}
