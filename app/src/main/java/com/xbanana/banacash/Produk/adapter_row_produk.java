package com.xbanana.banacash.Produk;

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

import com.xbanana.banacash.API.APInterface;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.DAO.ProdukDAO;
import com.xbanana.banacash.DAO.VoucherDAO;
import com.xbanana.banacash.R;
import com.xbanana.banacash.Voucher.adapter_row_voucher;
import com.xbanana.banacash.Voucher.edit_voucher;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adapter_row_produk extends RecyclerView.Adapter<adapter_row_produk.MyViewHolder> {
    private Context context;
    private List<ProdukDAO> result;

    public adapter_row_produk(Context context, List<ProdukDAO> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public adapter_row_produk.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_produk_layout,parent,false);
        final adapter_row_produk.MyViewHolder holder = new adapter_row_produk.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_row_produk.MyViewHolder holder, int position) {
        final ProdukDAO pro = result.get(position);
        holder.nama.setText(pro.getNama());
        holder.stock.setText(Integer.toString(pro.getStock()));
        holder.harga.setText(Integer.toString(pro.getHarga()));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(pro,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return result.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nama,stock,harga;
        private CardView parent;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            nama =  itemView.findViewById(R.id.nama_produk);
            stock = itemView.findViewById(R.id.stock_produk);
            harga = itemView.findViewById(R.id.harga_produk);
            parent = itemView.findViewById(R.id.parentProduk);
        }
    }
    @SuppressLint("PrivateResource")
    private void showDialog(final ProdukDAO hasil, int position){
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
                        deletedata(Integer.toString(hasil.getId()));
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
        APInterface apiService = ApiClient.getClient().create(APInterface.class);
        Call<Void> callDAO = apiService.deleteProduct(id);
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
    private void startIntentEdit(ProdukDAO hasil){
        Intent edit = new Intent(context, edit_produk.class);
        edit.putExtra("id",Integer.toString(hasil.getId()));
        edit.putExtra("nama_produk",hasil.getNama());
        edit.putExtra("harga_produk",Integer.toString(hasil.getHarga()));
        edit.putExtra("stock_produk",Integer.toString(hasil.getStock()));
        context.startActivity(edit);
    }
}
