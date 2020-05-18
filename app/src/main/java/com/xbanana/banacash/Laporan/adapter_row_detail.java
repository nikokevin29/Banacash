package com.xbanana.banacash.Laporan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.xbanana.banacash.DAO.DetailTransaksiDAO;
import com.xbanana.banacash.DAO.TransactionDAO;
import com.xbanana.banacash.R;

import java.util.List;

public class adapter_row_detail extends RecyclerView.Adapter<adapter_row_detail.MyViewHolder>{
    private Context context;
    private List<DetailTransaksiDAO> result;

    public adapter_row_detail(Context context, List<DetailTransaksiDAO> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public adapter_row_detail.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_show_detail_laporan,parent,false);
        final adapter_row_detail.MyViewHolder holder = new adapter_row_detail.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_row_detail.MyViewHolder holder, int position) {
        final DetailTransaksiDAO pro = result.get(position);
        holder.nama.setText(Integer.toString(pro.getId_produk()));
        holder.stock.setText(Integer.toString(pro.getJumlah()));
        holder.harga.setText(Double.toString(pro.getSubtotal()));
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nama,harga,stock;
        private CardView parent;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            nama =  itemView.findViewById(R.id.nama_produk);
            stock = itemView.findViewById(R.id.stock_produk);
            harga = itemView.findViewById(R.id.harga_produk);
            parent = itemView.findViewById(R.id.parentLaporan);
        }
    }
}
