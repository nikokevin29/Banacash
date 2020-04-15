package com.xbanana.banacash.Transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xbanana.banacash.API.APInterface;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.DAO.ProdukDAO;
import com.xbanana.banacash.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class view_pick_produk_transaksi extends AppCompatActivity {

    private List<ProdukDAO> produkList;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pick_produk_transaksi);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_pick_produk);
        produkList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new adapter_pick_produk_transaksi(view_pick_produk_transaksi.this,produkList);
        recyclerView.setAdapter(mAdapter);

        showData();
    }
    public void showData(){
        APInterface apiService = ApiClient.getClient().create(APInterface.class);
        Call<List<ProdukDAO>> voucherDAOCall = apiService.showAllProduct();
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Fetching product");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.show();
        voucherDAOCall.enqueue(new Callback<List<ProdukDAO>>() {
            @Override
            public void onResponse(Call<List<ProdukDAO>> call, Response<List<ProdukDAO>> response) {
                produkList.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                progress.dismiss();
                Toast.makeText(view_pick_produk_transaksi.this, "Product Displayed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ProdukDAO>> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(view_pick_produk_transaksi.this, "Bad Connection", Toast.LENGTH_SHORT).show();
                System.out.println("BANANAS"+t.getMessage());
            }
        });
    }
}
