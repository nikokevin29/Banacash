package com.xbanana.banacash.Voucher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xbanana.banacash.API.APInterface;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.DAO.VoucherDAO;
import com.xbanana.banacash.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kelola_voucher extends AppCompatActivity {

    private List<VoucherDAO> voucherList;
    private adapter_row_voucher voucherAdapter;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button AddVoucher;
    private EditText Kode,Persen;

    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private ConstraintLayout constraints;
    @SuppressLint("ClickableViewAccessibility")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelola_voucher);





        constraints = (ConstraintLayout) findViewById(R.id.constraint);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_voucher);
        voucherList = new ArrayList<>();
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new adapter_row_voucher(this,voucherList);
        recyclerView.setAdapter(mAdapter);

        showData();

        AddVoucher = findViewById(R.id.btnAddVoucher);
        AddVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInflater =(LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.pop_add_voucher,null);
                popupWindow = new PopupWindow(container,800,500,true);
                popupWindow.showAtLocation(constraints,Gravity.CENTER,0,0);
                //View popupView = layoutInflater.inflate(R.layout.pop_add_voucher, null);

//                Button SubmitAddVoucher = findViewById(R.id.btnSumbitAdd);
//                SubmitAddVoucher.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if(Kode.getText().length() == 0 ||Persen.getText().length() == 0){
//                            Toast.makeText(kelola_voucher.this, "Field Cant be Empty", Toast.LENGTH_SHORT).show();
//                        }else{
//                            addData();
//                            popupWindow.dismiss();
//                        }
//                    }
//                });
//                container.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        return true;
//                    }
//                });
            }
        });

    }
    public void addData(){
        APInterface apiService = ApiClient.getClient().create(APInterface.class);
        Call<List<VoucherDAO>> voucherDAO = apiService.showAllVoucher();

        voucherDAO.enqueue(new Callback<List<VoucherDAO>>() {
            @Override
            public void onResponse(Call<List<VoucherDAO>> call, Response<List<VoucherDAO>> response) {
                APInterface apiService = ApiClient.getClient().create(APInterface.class);
                Call<VoucherDAO> callDAO = apiService.createVoucher(Kode.getText().toString(),Persen.getText().toString());
                callDAO.enqueue(new Callback<VoucherDAO>() {
                    @Override
                    public void onResponse(Call<VoucherDAO> call, Response<VoucherDAO> response) {
                        Toast.makeText(kelola_voucher.this, "Success Adding Data", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<VoucherDAO> call, Throwable t) {
                        Toast.makeText(kelola_voucher.this, "Failed Enqueue", Toast.LENGTH_SHORT).show();
                        System.out.println("ERR_ADD"+t.getMessage());
                    }
                });
            }
            @Override
            public void onFailure(Call<List<VoucherDAO>> call, Throwable t) {
                System.out.println("gagal");
                Toast.makeText(kelola_voucher.this, "Failed create Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showData(){
        APInterface apiService = ApiClient.getClient().create(APInterface.class);
        Call<List<VoucherDAO>> voucherDAOCall = apiService.showAllVoucher();
                ProgressDialog progress = new ProgressDialog(this);
                progress.setMessage("Fetching data");
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progress.setCancelable(false);
                progress.show();
       voucherDAOCall.enqueue(new Callback<List<VoucherDAO>>() {
           @Override
           public void onResponse(Call<List<VoucherDAO>> call, Response<List<VoucherDAO>> response) {
                voucherList.addAll(response.body());
                mAdapter.notifyDataSetChanged();
                progress.dismiss();
                Toast.makeText(kelola_voucher.this, "Voucher Displayed", Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onFailure(Call<List<VoucherDAO>> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(kelola_voucher.this, "Bad Connection", Toast.LENGTH_SHORT).show();
                System.out.println("THEBEAST"+t.getMessage());
           }
       });
    }

}
