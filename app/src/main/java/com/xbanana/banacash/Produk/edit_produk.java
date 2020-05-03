package com.xbanana.banacash.Produk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xbanana.banacash.API.ApiInterface;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.DAO.ProdukDAO;
import com.xbanana.banacash.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_produk extends AppCompatActivity {
    private EditText nama,stock,harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_produk);
        nama = findViewById(R.id.etEditNamaProduk);
        stock =findViewById(R.id.etEditStockProduk);
        harga = findViewById(R.id.etEditHargaProduk);
        setField();
        Button btnSubmitEdit = findViewById(R.id.submitEditProduk);
        btnSubmitEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData();
                Intent i = new Intent(edit_produk.this, kelola_produk.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void editData(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProdukDAO> callDAO = apiService.updateProduct(getIntent().getStringExtra("id"),nama.getText().toString(),stock.getText().toString(),harga.getText().toString());

        callDAO.enqueue(new Callback<ProdukDAO>() {
            @Override
            public void onResponse(Call<ProdukDAO> call, Response<ProdukDAO> response) {
                Toast.makeText(edit_produk.this, "Success Edit Data", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ProdukDAO> call, Throwable t) {
                System.out.println("PRINTERR "+t.getMessage());
                Toast.makeText(edit_produk.this, "Failed Edit Data", Toast.LENGTH_SHORT).show();
                BacktoView();
            }
        });
    }
    public void setField(){
        nama.setText(getIntent().getStringExtra("nama_produk"));
        stock.setText(getIntent().getStringExtra("stock_produk"));
        harga.setText(getIntent().getStringExtra("harga_produk"));
    }
    public void BacktoView(){
        Intent acc = new Intent(edit_produk.this, kelola_produk.class);
        startActivity(acc);
        finish();
    }
}
