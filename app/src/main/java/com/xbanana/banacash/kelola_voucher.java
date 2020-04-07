package com.xbanana.banacash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.xbanana.banacash.API.APInterface;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.DAO.VoucherDAO;

import java.sql.SQLOutput;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kelola_voucher extends AppCompatActivity {
    ListView listView;
    private List<VoucherDAO> voucherList;
    private adapter_row_voucher voucherAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelola_voucher);

        //ArrayAdapter adapter = new ArrayAdapter(this,R.layout.list_view,voucherList);
        listView = findViewById(R.id.list_view);
        //listView.setAdapter(adapter);
        setVoucherList();
    }
    public void setVoucherList(){
        APInterface apiService = ApiClient.getClient().create(APInterface.class);
        Call<List<VoucherDAO>> voucherDAOCall = apiService.showAllVoucher();

        voucherDAOCall.enqueue(new Callback<List<VoucherDAO>>() {
            @Override
            public void onResponse(Call<List<VoucherDAO>> call, Response<List<VoucherDAO>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(kelola_voucher.this, "Show Voucher", Toast.LENGTH_SHORT).show();
                    voucherList.addAll(response.body());
                    System.out.println(response.body().get(0).getKode());
                }else {
                    Toast.makeText(kelola_voucher.this, "UnSuccessful Get Voucher",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<VoucherDAO>> call, Throwable t) {
                Toast.makeText(kelola_voucher.this, "Kesalahan Jaringan",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
