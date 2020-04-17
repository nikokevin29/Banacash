package com.xbanana.banacash.Transaksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xbanana.banacash.DAO.ProdukDAO;
import com.xbanana.banacash.R;
import com.xbanana.banacash.Static.StaticPickProduct;

import java.util.List;

public class adapter_row_input_produk_transaksi extends RecyclerView.Adapter<adapter_row_input_produk_transaksi.MyViewHolder> {
    private Context context;
    private List<ProdukDAO> result;

    public adapter_row_input_produk_transaksi(Context context, List<ProdukDAO> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public adapter_row_input_produk_transaksi.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_input_produk_transaksi,parent,false);
        final adapter_row_input_produk_transaksi.MyViewHolder holder = new adapter_row_input_produk_transaksi.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ProdukDAO pro = result.get(position);
        holder.nama.setText(pro.getNama());
        holder.stock.setText(Integer.toString(pro.getStock()));
        holder.harga.setText(Integer.toString(pro.getHarga()));
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nama,stock,harga;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            nama =  itemView.findViewById(R.id.tvNamaProduk);
            stock = itemView.findViewById(R.id.JumlahProduk);
            harga = itemView.findViewById(R.id.tvHargaProduk);
        }
    }
}
