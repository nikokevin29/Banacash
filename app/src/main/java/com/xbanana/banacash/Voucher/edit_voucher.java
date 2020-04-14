package com.xbanana.banacash.Voucher;

import android.app.*;
import android.content.*;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import retrofit2.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.xbanana.banacash.*;
import com.xbanana.banacash.API.APInterface;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.DAO.VoucherDAO;
public class edit_voucher extends AppCompatActivity {
    private EditText kode,diskon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_voucher);
        kode = findViewById(R.id.etEditKode);
        diskon =findViewById(R.id.etEditPersen);
        setField();
        Button btnSubmitEdit = findViewById(R.id.submitEditVoucher);
        btnSubmitEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData();
                Intent i = new Intent(edit_voucher.this,kelola_voucher.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void editData(){
        APInterface apiService = ApiClient.getClient().create(APInterface.class);
        Call<VoucherDAO> callDAO = apiService.updateVoucher(getIntent().getStringExtra("id"),kode.getText().toString(),diskon.getText().toString());

        callDAO.enqueue(new Callback<VoucherDAO>() {
            @Override
            public void onResponse(Call<VoucherDAO> call, Response<VoucherDAO> response) {
                Toast.makeText(edit_voucher.this, "Success Edit Data", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<VoucherDAO> call, Throwable t) {
                System.out.println("PRINTERR "+t.getMessage());
                Toast.makeText(edit_voucher.this, "Failed Edit Data", Toast.LENGTH_SHORT).show();
                BacktoView();
            }
        });
    }
    public void setField(){
        kode.setText(getIntent().getStringExtra("kode"));
        diskon.setText(getIntent().getStringExtra("diskon"));
    }
    public void BacktoView(){
        Intent acc = new Intent(edit_voucher.this, kelola_voucher.class);
        startActivity(acc);
        finish();
    }

}
