package com.xbanana.banacash.Laporan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.API.ApiInterface;
import com.xbanana.banacash.DAO.DetailTransaksiDAO;
import com.xbanana.banacash.DAO.TransactionDAO;
import com.xbanana.banacash.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDetailLaporan extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<DetailTransaksiDAO> detailTransaksiDAOS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pick_produk_transaksi);
        detailTransaksiDAOS = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_pick_produk);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new adapter_row_detail(ShowDetailLaporan.this,detailTransaksiDAOS);
        recyclerView.setAdapter(mAdapter);
        showData();
    }
    public void showData(){
        Intent intent = getIntent();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DetailTransaksiDAO>> laporanDAOCall = apiService.showDetailById(Integer.parseInt(intent.getStringExtra("id")));
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Fetching data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.show();
        laporanDAOCall.enqueue(new Callback<List<DetailTransaksiDAO>>() {
            @Override
            public void onResponse(Call<List<DetailTransaksiDAO>> call, Response<List<DetailTransaksiDAO>> response) {
                detailTransaksiDAOS.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                progress.dismiss();
                Toast.makeText(ShowDetailLaporan.this, "Product Displayed", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<DetailTransaksiDAO>> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(ShowDetailLaporan.this, "Bad Connection", Toast.LENGTH_SHORT).show();
                System.out.println("THEBEAST"+t.getMessage());
            }
        });
    }
}
