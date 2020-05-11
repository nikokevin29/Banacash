package com.xbanana.banacash.Transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
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
    Button tbBtnCreateTransaksi;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    EditText namaCust,voucher;
    private double tempsubtotal = 0;
    int incr = 1;
    String datenow,pegawai="Kelvin";
    ProgressDialog progress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelola_transaksi);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_transaksi);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        progress= new ProgressDialog(this);
        SharedPreferences id_transaksi = getSharedPreferences("idTransaksi", Context.MODE_PRIVATE);
        incr = id_transaksi.getInt("incr", 1);

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        datenow = dtf.format(now);
        voucher = findViewById(R.id.etextvoucher);
        total = findViewById(R.id.valuetotalharga);

        total.setText(String.valueOf(tempsubtotal));
        namaCust = findViewById(R.id.ETcustomername);
        tbBtnCreateTransaksi = findViewById(R.id.ButtonCreate);
        tbBtnCreateTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buatTransaksi();
            }
        });
    }
    private void buatTransaksi(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransactionDAO> callDAO = apiService.createTransaction(
                incr,
                namaCust.getText().toString(),
                tempsubtotal,
                datenow,
                pegawai);
        callDAO.enqueue(new Callback<TransactionDAO>() {
            @Override
            public void onResponse(Call<TransactionDAO> call, Response<TransactionDAO> response) {
                Toast.makeText(view_kelola_transaksi.this, "Gagal Transaksi, tapi Boong", Toast.LENGTH_SHORT).show();
                System.out.println("id "+incr);
                System.out.println("tempsubtotal "+tempsubtotal);
                System.out.println("datenow "+ datenow);
                System.out.println("Pegawai"+pegawai);
                SharedPreferences id_transaksi = getSharedPreferences("idTransaksi", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = id_transaksi.edit();
                incr = incr +1;
                editor.putInt("incr", incr);
                editor.commit();
                System.out.println("namaCust "+namaCust.getText().toString());
            }
            @Override
            public void onFailure(Call<TransactionDAO> call, Throwable t) {
                Toast.makeText(view_kelola_transaksi.this, " failed", Toast.LENGTH_SHORT).show();
            }
        });
        progress.dismiss();

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
                double temp = StaticPickProduct.details.get(i).getSubtotal();
                tempsubtotal = tempsubtotal + temp;
            }
            total.setText(Double.toString(tempsubtotal));
        }
    }

    public void CekVoucher()
    {

    }

    private boolean validasi()
    {
        int cek = 0;

        if(namaCust.getText().toString().equals(""))
        {
            namaCust.setError("Nama Customer Tidak Boleh Kosong");
            cek = 1;
        }
        return cek == 0;
    }
}
