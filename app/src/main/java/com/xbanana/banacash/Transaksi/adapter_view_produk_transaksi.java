package com.xbanana.banacash.Transaksi;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.API.ApiInterface;
import com.xbanana.banacash.DAO.DetailTransaksiDAO;
import com.xbanana.banacash.DAO.ProdukDAO;
import com.xbanana.banacash.R;
import com.xbanana.banacash.Static.StaticPickProduct;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adapter_view_produk_transaksi extends RecyclerView.Adapter<adapter_view_produk_transaksi.MyViewHolder> {
    private view_kelola_transaksi context;
    private List<DetailTransaksiDAO> result;
    public double harga;
    public adapter_view_produk_transaksi(view_kelola_transaksi context, List<DetailTransaksiDAO> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public adapter_view_produk_transaksi.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_view_produk_transaksi,parent,false);
        final adapter_view_produk_transaksi.MyViewHolder holder = new adapter_view_produk_transaksi.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService.getbyidProduk(result.get(position).getId_produk()).enqueue(new Callback<ProdukDAO>() {
            @Override
            public void onResponse(Call<ProdukDAO> call, Response<ProdukDAO> response) {
                holder.nama.setText(response.body().getNama());
                holder.harga.setText(String.valueOf(response.body().getHarga()));
                holder.jumlah.setText(String.valueOf(result.get(position).getJumlah()));
                harga = response.body().getHarga();
                context.subtotalFromRecycleTransaksi();
            }
            @Override
            public void onFailure(Call<ProdukDAO> call, Throwable t) { }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nama,jumlah,harga;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            nama =  itemView.findViewById(R.id.tvNamaProduk);
            jumlah = itemView.findViewById(R.id.JumlahProduk);
            harga = itemView.findViewById(R.id.tvHargaProduk);
        }
    }
}
