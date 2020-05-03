package com.xbanana.banacash.Transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.API.ApiInterface;
import com.xbanana.banacash.DAO.TransactionDAO;
import com.xbanana.banacash.R;
import com.xbanana.banacash.Static.StaticPickProduct;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class view_kelola_transaksi extends AppCompatActivity {
    TextView tvBtnPickProduct,total;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    EditText namaCust;
    int tempsubtotal = 0;
    int id_tranaksi = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelola_transaksi);
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
        total.setText(Integer.toString(tempsubtotal));
        namaCust = findViewById(R.id.ETcustomername);

    }
    private void buatTransaksi(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String datenow = dtf.format(now);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransactionDAO> callDAO = apiService.createTransaction(
                namaCust.getText().toString(),
                Double.valueOf(tempsubtotal),
                datenow,
                "Kelvin");
        callDAO.enqueue(new Callback<TransactionDAO>() {
            @Override
            public void onResponse(Call<TransactionDAO> call, Response<TransactionDAO> response) {
                    
            }

            @Override
            public void onFailure(Call<TransactionDAO> call, Throwable t) {

            }
        });

    }

    private void getLastIdTransaksi(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TransactionDAO>> callDAO = apiInterface.showAllTransaction();
        callDAO.enqueue(new Callback<List<TransactionDAO>>() {
            @Override
            public void onResponse(Call<List<TransactionDAO>> call, Response<List<TransactionDAO>> response) {
                for (int i = 0;i<response.body().size();i++){
                    id_tranaksi = response.body().get(i).getId();
                }
            }

            @Override
            public void onFailure(Call<List<TransactionDAO>> call, Throwable t) {

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
    public void subtotalFromRecycleTransaksi() {
        if (StaticPickProduct.details != null) {
            for (int i = 0; i < StaticPickProduct.details.size(); i++) {
                int temp = (int) Math.round(StaticPickProduct.details.get(i).getSubtotal());
                tempsubtotal = tempsubtotal + temp;
            }
            total.setText(Integer.toString(tempsubtotal));
        }
    }
}
