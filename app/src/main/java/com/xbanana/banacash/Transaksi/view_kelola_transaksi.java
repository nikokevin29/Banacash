package com.xbanana.banacash.Transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.API.ApiInterface;
import com.xbanana.banacash.DAO.TransactionDAO;
import com.xbanana.banacash.R;
import com.xbanana.banacash.Static.StaticPickProduct;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class view_kelola_transaksi extends AppCompatActivity {
    TextView tvBtnPickProduct,total;
    Button submit_create;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    EditText namaCust;
    double tempsubtotal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelola_transaksi);
        if(StaticPickProduct.selectProduct==null){
            StaticPickProduct.selectProduct = new ArrayList<>();
        }
        submit_create = findViewById(R.id.ButtonCreate);
        submit_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buatTransaksi();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_transaksi);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new adapter_view_produk_transaksi(view_kelola_transaksi.this, StaticPickProduct.details);
        recyclerView.setAdapter(mAdapter);
        tvBtnPickProduct = findViewById(R.id.txtaddproducts);
        tvBtnPickProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view_kelola_transaksi.this,view_pick_produk_transaksi.class);
                startActivity(i);
            }
        });
        total = findViewById(R.id.valuetotalharga);
        namaCust = findViewById(R.id.ETcustomername);

    }
    private void buatTransaksi(){
        int init0 = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String datenow = dtf.format(now);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransactionDAO> callDAO = apiService.createTransaction(
                0,
                namaCust.getText().toString(),
                tempsubtotal,
                datenow,
                "Robin");
        callDAO.enqueue(new Callback<TransactionDAO>() {
            @Override
            public void onResponse(Call<TransactionDAO> call, Response<TransactionDAO> response) {
                Toast.makeText(view_kelola_transaksi.this, "Sukses", Toast.LENGTH_SHORT).show();
                StaticPickProduct.details.clear();//clear seletah tekan proses jika proses berhasil
            }

            @Override
            public void onFailure(Call<TransactionDAO> call, Throwable t) {
                Toast.makeText(view_kelola_transaksi.this, "Gagal "+t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("BNN"+t.getMessage());
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        StaticPickProduct.details.clear();//menghapus isi Static Produk ketika menenak tombol back
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
        subtotalFromRecycleTransaksi();
    }
    public void subtotalFromRecycleTransaksi(){

        for(int i=0;i<StaticPickProduct.details.size();i++){
            tempsubtotal =  tempsubtotal + StaticPickProduct.details.get(i).getSubtotal();
        }
        total.setText(String.valueOf(tempsubtotal));
    }
}
