package com.xbanana.banacash.Transaksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xbanana.banacash.DAO.DetailTransaksiDAO;
import com.xbanana.banacash.DAO.ProdukDAO;
import com.xbanana.banacash.R;
import com.xbanana.banacash.Static.StaticPickProduct;

import java.util.ArrayList;
import java.util.List;

public class adapter_pick_produk_transaksi extends RecyclerView.Adapter<adapter_pick_produk_transaksi.MyViewHolder> {
    private view_pick_produk_transaksi context;
    private List<ProdukDAO> result;
    public String jumlah = "";
    public double subtotal = 0;
    public adapter_pick_produk_transaksi(view_pick_produk_transaksi context, List<ProdukDAO> result){
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


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Transaction");
                builder.setMessage("Input Value of Product:");
                final EditText input = new EditText(context);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                input.setHint("jumlah");
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jumlah = input.getText().toString().trim();
                        if(Integer.parseInt(jumlah) <= 0){
                            Toast.makeText(context, "Empty Field", Toast.LENGTH_SHORT).show();
                        }else {
                            subtotal = pro.getHarga() * Double.parseDouble(jumlah);
                            StaticPickProduct.details.add(new DetailTransaksiDAO(0, String.valueOf(pro.getId()),Integer.parseInt(jumlah), subtotal));
                        }
                    }
                });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
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


}
