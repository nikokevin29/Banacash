package com.xbanana.banacash.Produk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.xbanana.banacash.API.APInterface;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.DAO.ProdukDAO;
import com.xbanana.banacash.DAO.VoucherDAO;
import com.xbanana.banacash.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pop_up_produk_add extends AppCompatDialogFragment {
    private EditText nama,stock,harga;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.pop_add_produk,null);
        nama = (EditText)view.findViewById(R.id.etAddNama);
        stock = (EditText)view.findViewById(R.id.etAddStock);
        harga = (EditText)view.findViewById(R.id.etAddHarga);

        builder.setView(view).setTitle("Add New Voucher").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addData();
                Toast.makeText(getActivity(),"Success Create Product",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        return builder.create();
    }
    public void addData() {
        APInterface apiService = ApiClient.getClient().create(APInterface.class);
        Call<ProdukDAO> callDAO = apiService.createProduct(nama.getText().toString(), stock.getText().toString(),harga.getText().toString());
        callDAO.enqueue(new Callback<ProdukDAO>() {
            @Override
            public void onResponse(Call<ProdukDAO> call, Response<ProdukDAO> response) {
            }

            @Override
            public void onFailure(Call<ProdukDAO> call, Throwable t) {
                System.out.println("ERR_ADD" + t.getMessage());
            }
        });
    }
}

