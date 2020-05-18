package com.xbanana.banacash.Laporan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.xbanana.banacash.DAO.TransactionDAO;
import com.xbanana.banacash.R;

import java.util.List;

public class adapter_row_laporan extends RecyclerView.Adapter<adapter_row_laporan.MyViewHolder>{
    private Context context;
    private List<TransactionDAO> result;

    public adapter_row_laporan(Context context, List<TransactionDAO> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public adapter_row_laporan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_report_layout,parent,false);
        final adapter_row_laporan.MyViewHolder holder = new adapter_row_laporan.MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_row_laporan.MyViewHolder holder, int position) {
        final TransactionDAO pro = result.get(position);
        holder.nama.setText(pro.getNama_customer());
        holder.harga.setText(Double.toString(pro.getTotal_harga()));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  =  new Intent(context,ShowDetailLaporan.class);
                i.putExtra("id",Integer.toString(pro.getIds()));
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return result.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nama,harga;
        private LinearLayout parent;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            nama =  itemView.findViewById(R.id.nama_produk);
            harga = itemView.findViewById(R.id.total_keuntungan);
            parent = itemView.findViewById(R.id.parentLaporan);
        }
    }
}
