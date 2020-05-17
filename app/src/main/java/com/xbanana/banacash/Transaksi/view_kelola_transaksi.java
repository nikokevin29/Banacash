package com.xbanana.banacash.Transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.API.ApiInterface;
import com.xbanana.banacash.DAO.DetailTransaksiDAO;
import com.xbanana.banacash.DAO.TransactionDAO;
import com.xbanana.banacash.DAO.VoucherDAO;
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
    Button submit_create,btnCheckVoucher;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    EditText namaCust,etVoucher;
    double tempsubtotal = 0;
    int incr = 1;
    int lastid = 0;
    String getVoucher = null;
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
        etVoucher = findViewById(R.id.etextvoucher);
        btnCheckVoucher = findViewById(R.id.cekVoucher);
        btnCheckVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekVoucher();
            }
        });
    }
    private  void cekVoucher(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<VoucherDAO>> callDAO  = apiService.showAllVoucher();
        callDAO.enqueue(new Callback<List<VoucherDAO>>() {
            @Override
            public void onResponse(Call<List<VoucherDAO>> call, Response<List<VoucherDAO>> response) {
                for(int i = 0; i<response.body().size(); i++){
                    if(etVoucher.getText().toString().equals(response.body().get(i).getKode())){
                        getVoucher = response.body().get(i).getKode();
                        etVoucher.setText(getVoucher);
                        double realtot = tempsubtotal - response.body().get(i).getDiskon();
                        total.setText(String.valueOf(realtot));
                        Toast.makeText(view_kelola_transaksi.this, "Voucher Applied", Toast.LENGTH_SHORT).show();
                    }
                }
                if(getVoucher == null){
                    Toast.makeText(view_kelola_transaksi.this, "Voucher Code Not Found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<VoucherDAO>> call, Throwable t) {
                System.out.println("Failed get all Voucher");
            }
        });
    }
    private void buatTransaksi(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        String datenow = dtf.format(now);

        SharedPreferences id_transaksi = getSharedPreferences("idTransaksi", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = id_transaksi.edit();
        incr = incr +1;
        editor.putInt("incr", incr);
        editor.apply();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransactionDAO> callDAO = apiService.createTransaction(
                incr,
                namaCust.getText().toString(),
                tempsubtotal,
                datenow,
                "Robin");
        callDAO.enqueue(new Callback<TransactionDAO>() {
            @Override
            public void onResponse(Call<TransactionDAO> call, Response<TransactionDAO> response) {
                    //if Failed Reverse
            }
            @Override
            public void onFailure(Call<TransactionDAO> call, Throwable t) {
                System.out.println("Data Stored but  "+t.getMessage());
                Toast.makeText(view_kelola_transaksi.this, "Header Transaksi Created", Toast.LENGTH_SHORT).show();
                buatDetil();
            }
        });
    }

    private void buatDetil(){
        //getlastid
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransactionDAO> callDAO = apiService.getLastid();
        callDAO.enqueue(new Callback<TransactionDAO>() {
            @Override
            public void onResponse(Call<TransactionDAO> call, Response<TransactionDAO> response) {
                lastid = response.body().getIds();
                System.out.println(lastid+" adalah last id transaksi");
                //detil
                for(int i = 0;i<StaticPickProduct.details.size();i++){
                    Call<DetailTransaksiDAO> callDetil =apiService.createDetail(
                            lastid,
                            StaticPickProduct.details.get(i).getId_produk(),
                            StaticPickProduct.details.get(i).getJumlah(),
                            StaticPickProduct.details.get(i).getSubtotal()
                    );
                    callDetil.enqueue(new Callback<DetailTransaksiDAO>() {
                        @Override
                        public void onResponse(Call<DetailTransaksiDAO> call, Response<DetailTransaksiDAO> response) {
                            Toast.makeText(view_kelola_transaksi.this, "Stored Data to Transcation :"+lastid, Toast.LENGTH_SHORT).show();
                            StaticPickProduct.details.clear();//clear array adapter detil
                            namaCust.getText().clear();//clear nama customer
                            etVoucher.getText().clear();//clear data voucher
                            subtotalFromRecycleTransaksi();//update Subtotal TextView
                            mAdapter.notifyDataSetChanged();//update state adapter setelah transaksi selesai(state pasti akan kosong)
                        }

                        @Override
                        public void onFailure(Call<DetailTransaksiDAO> call, Throwable t) {
                            System.out.println("Detil Failed");
                        }
                    });
                }//end for
            }//end onResponse get last id

            @Override
            public void onFailure(Call<TransactionDAO> call, Throwable t) {
                Toast.makeText(view_kelola_transaksi.this, "Get last id failed", Toast.LENGTH_SHORT).show();
                System.out.println("gagal get last id");
                System.out.println(t.getMessage());
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
