package com.xbanana.banacash.Voucher;

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
import com.xbanana.banacash.DAO.VoucherDAO;
import com.xbanana.banacash.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kelola_voucher extends AppCompatActivity {

    private List<VoucherDAO> voucherList;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button AddVoucher;

    @SuppressLint("ClickableViewAccessibility")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelola_voucher);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_voucher);
        voucherList = new ArrayList<>();
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new adapter_row_voucher(this,voucherList);
        recyclerView.setAdapter(mAdapter);

        showData();

        AddVoucher = findViewById(R.id.btnAddVoucher);
        AddVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
                mAdapter.notifyDataSetChanged();
            }
        });

    }
    public void openDialog(){
        pop_up_voucher_add dialog = new pop_up_voucher_add();
        dialog.show(getSupportFragmentManager(),"Create Voucher");
    }
    public void showData(){
        APInterface apiService = ApiClient.getClient().create(APInterface.class);
        Call<List<VoucherDAO>> voucherDAOCall = apiService.showAllVoucher();
                ProgressDialog progress = new ProgressDialog(this);
                progress.setMessage("Fetching data");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(false);
                progress.show();
       voucherDAOCall.enqueue(new Callback<List<VoucherDAO>>() {
           @Override
           public void onResponse(Call<List<VoucherDAO>> call, Response<List<VoucherDAO>> response) {
                voucherList.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                progress.dismiss();
                Toast.makeText(kelola_voucher.this, "Voucher Displayed", Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onFailure(Call<List<VoucherDAO>> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(kelola_voucher.this, "Bad Connection", Toast.LENGTH_SHORT).show();
                System.out.println("THEBEAST"+t.getMessage());
           }
       });
    }

}
