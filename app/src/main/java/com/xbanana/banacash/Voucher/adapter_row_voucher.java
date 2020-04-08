package com.xbanana.banacash.Voucher;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.xbanana.banacash.DAO.VoucherDAO;
import com.xbanana.banacash.R;

import java.util.List;

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
        holder.diskon.setText(voucherDAO.getDiskon());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putString("kode", voucherDAO.getKode());
                data.putString("diskon", voucherDAO.getDiskon());

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
        public void onClick(View view)
        {
            Toast.makeText(context, "Touched", Toast.LENGTH_SHORT).show();
        }
    }



}
