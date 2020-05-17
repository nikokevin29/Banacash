package com.xbanana.banacash.Voucher;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.xbanana.banacash.API.ApiInterface;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.DAO.VoucherDAO;
import com.xbanana.banacash.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adapter_row_voucher extends RecyclerView.Adapter<adapter_row_voucher.MyViewHolder> {
    private Context context;
    private List<VoucherDAO> result;

    public adapter_row_voucher(Context context, List<VoucherDAO> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_voucher_layout,parent,false);
        final MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_row_voucher.MyViewHolder holder, int position) {
        final VoucherDAO voucherDAO = result.get(position);
        holder.kode.setText(voucherDAO.getKode());
        holder.diskon.setText(String.valueOf(voucherDAO.getDiskon()));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(voucherDAO,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return result.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView kode, diskon;
        private CardView parent;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            kode = itemView.findViewById(R.id.kode);
            diskon =  itemView.findViewById(R.id.diskon);
            parent =  itemView.findViewById(R.id.parentVoucher);
        }
    }
    @SuppressLint("PrivateResource")
    private void showDialog(final VoucherDAO hasil, int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder .setTitle("Aksi apa yang akan anda lakukan?")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Edit",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        startIntentEdit(hasil);
                    }
                })
                .setNegativeButton("Hapus",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deletedata(hasil.getId());
                        notifyItemRemoved(position);
                        result.remove(position);
                    }
                })
                .setNeutralButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                }).show();
    }
    private void deletedata(String id){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Void> callDAO = apiService.deleteVoucher(id);
        callDAO.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(context, "Success Delete", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Failed Delete", Toast.LENGTH_SHORT).show();
                System.out.println("ERROR "+t.getMessage());
            }
        });
    }
    private void startIntentEdit(VoucherDAO hasil){
        Intent edit = new Intent(context, edit_voucher.class);
        edit.putExtra("id",hasil.getId());
        edit.putExtra("kode",hasil.getKode());
        edit.putExtra("diskon",hasil.getDiskon());
        context.startActivity(edit);
    }
}
