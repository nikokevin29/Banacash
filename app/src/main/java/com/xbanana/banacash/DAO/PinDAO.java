package com.xbanana.banacash.DAO;

import com.google.gson.annotations.SerializedName;

public class PinDAO {
    @SerializedName("id")
    int id;
    @SerializedName("pin")
    int pin;
    @SerializedName("nama_pegawai")
    String nama_pegawai;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getNama() {
        return nama_pegawai;
    }

    public void setNama(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public PinDAO(int id, int pin, String nama_pegawai) {
        this.id = id;
        this.pin = pin;
        this.nama_pegawai = nama_pegawai;
    }
}
