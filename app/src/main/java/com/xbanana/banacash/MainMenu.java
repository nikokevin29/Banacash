package com.xbanana.banacash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xbanana.banacash.Produk.kelola_produk;
import com.xbanana.banacash.Transaksi.view_kelola_transaksi;

public class MainMenu extends AppCompatActivity {
    ImageView kelola_produk,kelola_transaksi,kelola_voucher,kelola_laporan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        kelola_laporan = findViewById(R.id.Kelola_laporan);
        kelola_voucher = findViewById(R.id.Kelola_voucher);
        kelola_transaksi = findViewById(R.id.Kelola_transaksi);
        kelola_produk = findViewById(R.id.Kelola_produk);

        initImageButton();
    }

    public void initImageButton(){
        kelola_laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, com.xbanana.banacash.Laporan.kelola_laporan.class);
                startActivity(i);
            }
        });
        kelola_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, com.xbanana.banacash.Produk.kelola_produk.class);
                startActivity(i);
            }
        });
        kelola_voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, com.xbanana.banacash.Voucher.kelola_voucher.class);
                startActivity(i);
            }
        });
        kelola_transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, view_kelola_transaksi.class);
                startActivity(i);
            }
        });
        kelola_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, kelola_produk.class);
                startActivity(i);
            }
        });
    }
}
