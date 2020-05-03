package com.xbanana.banacash.Transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xbanana.banacash.DAO.ProdukDAO;
import com.xbanana.banacash.Produk.adapter_row_produk;
import com.xbanana.banacash.Produk.kelola_produk;
import com.xbanana.banacash.R;
import com.xbanana.banacash.Static.StaticPickProduct;

import java.util.ArrayList;
import java.util.List;

public class kelola_transaksi extends AppCompatActivity {
    TextView tvBtnPickProduct;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelola_transaksi);
        if(StaticPickProduct.selectProduct==null){
            StaticPickProduct.selectProduct = new ArrayList<>();
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_transaksi);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new adapter_row_input_produk_transaksi(kelola_transaksi.this, StaticPickProduct.selectProduct);
        recyclerView.setAdapter(mAdapter);
        tvBtnPickProduct = findViewById(R.id.txtaddproducts);
        tvBtnPickProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(kelola_transaksi.this,view_pick_produk_transaksi.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        StaticPickProduct.selectProduct.clear();//menghapus isi Static Produk ketika menenak tombol back
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
}
