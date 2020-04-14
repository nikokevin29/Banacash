package com.xbanana.banacash.Produk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xbanana.banacash.API.APInterface;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.DAO.ProdukDAO;
import com.xbanana.banacash.DAO.VoucherDAO;
import com.xbanana.banacash.R;
import com.xbanana.banacash.Voucher.adapter_row_voucher;
import com.xbanana.banacash.Voucher.kelola_voucher;
import com.xbanana.banacash.Voucher.pop_up_voucher_add;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kelola_produk extends AppCompatActivity {
    private List<ProdukDAO> produkList;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button AddProduk;

    @SuppressLint("ClickableViewAccessibility")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelola_produk);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_produk);
        produkList = new ArrayList<>();
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new adapter_row_produk(kelola_produk.this,produkList);
        recyclerView.setAdapter(mAdapter);

        showData();

        AddProduk = findViewById(R.id.btnAddProduk);
        AddProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
                mAdapter.notifyDataSetChanged();
            }
        });
    }
    public void openDialog(){
        pop_up_produk_add dialog = new pop_up_produk_add();
        dialog.show(getSupportFragmentManager(),"Create Produk");
    }
    public void showData(){
        APInterface apiService = ApiClient.getClient().create(APInterface.class);
        Call<List<ProdukDAO>> voucherDAOCall = apiService.showAllProduct();
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Fetching data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.show();
        voucherDAOCall.enqueue(new Callback<List<ProdukDAO>>() {
            @Override
            public void onResponse(Call<List<ProdukDAO>> call, Response<List<ProdukDAO>> response) {
                produkList.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                progress.dismiss();
                Toast.makeText(kelola_produk.this, "Product Displayed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ProdukDAO>> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(kelola_produk.this, "Bad Connection", Toast.LENGTH_SHORT).show();
                System.out.println("THEBEAST"+t.getMessage());
            }
        });
    }
}
