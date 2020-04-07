package com.xbanana.banacash.DAO;

import com.google.gson.annotations.SerializedName;

public class VoucherDAO {
    @SerializedName("id")
    int id;
    @SerializedName("kode")
    String kode;
    @SerializedName("diskon")
    float diskon;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public float getDiskon() {
        return diskon;
    }

    public void setDiskon(float diskon) {
        this.diskon = diskon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VoucherDAO(int id, String kode, float diskon) {
        this.id = id;
        this.kode = kode;
        this.diskon = diskon;
    }
}
