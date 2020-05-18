package com.xbanana.banacash.Laporan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.API.ApiInterface;
import com.xbanana.banacash.DAO.ProdukDAO;
import com.xbanana.banacash.DAO.TransactionDAO;
import com.xbanana.banacash.Produk.kelola_produk;
import com.xbanana.banacash.R;
import com.xbanana.banacash.Voucher.adapter_row_voucher;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kelola_laporan extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<TransactionDAO> transactionDAOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelola_laporan);
        transactionDAOS = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_report);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new adapter_row_laporan(kelola_laporan.this,transactionDAOS);
        recyclerView.setAdapter(mAdapter);
        showData();
    }
    public void showData(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<TransactionDAO>> laporanDAOCall = apiService.showAllTransaction();
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Fetching data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.show();
        laporanDAOCall.enqueue(new Callback<List<TransactionDAO>>() {
            @Override
            public void onResponse(Call<List<TransactionDAO>> call, Response<List<TransactionDAO>> response) {
                transactionDAOS.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                progress.dismiss();
                Toast.makeText(kelola_laporan.this, "Product Displayed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<TransactionDAO>> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(kelola_laporan.this, "Bad Connection", Toast.LENGTH_SHORT).show();
                System.out.println("THEBEAST"+t.getMessage());
            }
        });
    }
}
