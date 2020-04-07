package com.xbanana.banacash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xbanana.banacash.API.APInterface;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.DAO.VoucherDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLOutput;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class kelola_voucher extends AppCompatActivity {
    ListView listView;
    private List<VoucherDAO> voucherList;
    private adapter_row_voucher voucherAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelola_voucher);

        //ArrayAdapter adapter = new ArrayAdapter(this,R.layout.list_view,voucherList);
        listView = findViewById(R.id.list_view);
        //listView.setAdapter(adapter);
        setVoucherList();
    }
    public void setVoucherList(){
        String url = "http://banacash.luxinoire.com/api/showVoucher";
        RequestQueue queue = Volley.newRequestQueue(kelola_voucher.this);

        StringRequest getRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i<jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                            VoucherDAO voucher = new VoucherDAO(obj.getInt("id"),obj.getString("kode"),obj.getInt("diskon"));
                            voucherList.add(voucher);
                    }
                    Toast.makeText(kelola_voucher.this, "Show Voucher", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Toast.makeText(kelola_voucher.this,"Connection Error" ,Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(getRequest);
//        APInterface apiService = ApiClient.getClient().create(APInterface.class);
//        Call<List<VoucherDAO>> voucherDAOCall = apiService.showAllVoucher();
//
//        voucherDAOCall.enqueue(new Callback<List<VoucherDAO>>() {
//            @Override
//            public void onResponse(Call<List<VoucherDAO>> call, Response<List<VoucherDAO>> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(kelola_voucher.this, "Show Voucher", Toast.LENGTH_SHORT).show();
//                    voucherList.addAll(response.body());
//                    System.out.println(response.body().get(0).getKode());
//                }else {
//                    Toast.makeText(kelola_voucher.this, "UnSuccessful Get Voucher",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<VoucherDAO>> call, Throwable t) {
//                if (t instanceof IOException) {
//                    Toast.makeText(kelola_voucher.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
//                    // logging probably not necessary
//                }
//                else {
//                    Toast.makeText(kelola_voucher.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
//                    // todo log to some central bug tracking service
//                }
//            }
//        });
    }
}
