package com.xbanana.banacash.Transaksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xbanana.banacash.DAO.ProdukDAO;
import com.xbanana.banacash.R;

import java.util.List;

public class adapter_pick_produk_transaksi extends RecyclerView.Adapter<adapter_pick_produk_transaksi.MyViewHolder> {
    private Context context;
    private List<ProdukDAO> result;

    public adapter_pick_produk_transaksi(Context context, List<ProdukDAO> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public adapter_pick_produk_transaksi.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_pick_produk_transaksi,parent,false);
        final adapter_pick_produk_transaksi.MyViewHolder holder = new adapter_pick_produk_transaksi.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_pick_produk_transaksi.MyViewHolder holder, int position) {
        final ProdukDAO pro = result.get(position);
        holder.nama.setText(pro.getNama());
        holder.stock.setText(Integer.toString(pro.getStock()));
        holder.harga.setText(Integer.toString(pro.getHarga()));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Pick "+pro.getNama(), Toast.LENGTH_SHORT).show();
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


    private void startIntent(ProdukDAO hasil){
        Intent edit = new Intent(context, kelola_transaksi.class);
        edit.putExtra("id",Integer.toString(hasil.getId()));
        edit.putExtra("nama_produk",hasil.getNama());
        edit.putExtra("harga_produk",Integer.toString(hasil.getHarga()));
        edit.putExtra("stock_produk",Integer.toString(hasil.getStock()));
        context.startActivity(edit);
    }
}
