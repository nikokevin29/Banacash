package com.xbanana.banacash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xbanana.banacash.DAO.VoucherDAO;

import java.util.ArrayList;

public class adapter_row_voucher extends BaseAdapter {
    private Context context;
    private ArrayList<VoucherDAO> voucherArrayList;

    public adapter_row_voucher(Context context, ArrayList<VoucherDAO> voucherArrayList){
        this.context = context;
        this.voucherArrayList = voucherArrayList;
    }
    @Override
    public int getCount() {
        return voucherArrayList.size();
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public Object getItem(int position) {
        return voucherArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_voucher_layout, null, true);

            holder.tvkode = (TextView) convertView.findViewById(R.id.kode);
            holder.tvdiskon = (TextView) convertView.findViewById(R.id.diskon);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tvkode.setText("Name: "+voucherArrayList.get(position).getKode());
        holder.tvkode.setText("Country: "+voucherArrayList.get(position).getDiskon());
        return convertView;
    }

    private class ViewHolder {
        protected TextView tvkode, tvdiskon;
    }
}
