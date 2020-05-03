package com.xbanana.banacash.Voucher;

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

import com.xbanana.banacash.API.ApiInterface;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.DAO.VoucherDAO;
import com.xbanana.banacash.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pop_up_voucher_add extends AppCompatDialogFragment {
    private EditText kode,diskon;
    @NonNull
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.pop_add_voucher,null);
        kode = (EditText)view.findViewById(R.id.etAddKode);
        diskon = (EditText)view.findViewById(R.id.etAddPersentase);

        builder.setView(view).setTitle("Add New Voucher").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               addData();
               Toast.makeText(getActivity(),"Success Create Voucher Code",Toast.LENGTH_SHORT).show();
               dialog.dismiss();
            }
        });
        return builder.create();
    }
    public void addData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<VoucherDAO>> voucherDAO = apiService.showAllVoucher();

        voucherDAO.enqueue(new Callback<List<VoucherDAO>>() {
            @Override
            public void onResponse(Call<List<VoucherDAO>> call, Response<List<VoucherDAO>> response) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<VoucherDAO> callDAO = apiService.createVoucher(kode.getText().toString(), diskon.getText().toString());
                callDAO.enqueue(new Callback<VoucherDAO>() {
                    @Override
                    public void onResponse(Call<VoucherDAO> call, Response<VoucherDAO> response) {
                    }

                    @Override
                    public void onFailure(Call<VoucherDAO> call, Throwable t) {
                        System.out.println("ERR_ADD" + t.getMessage());
                    }
                });
            }
            @Override
            public void onFailure(Call<List<VoucherDAO>> call, Throwable t) {
            }
        });
    }
}
