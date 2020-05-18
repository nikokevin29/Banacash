package com.xbanana.banacash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xbanana.banacash.API.ApiInterface;
import com.xbanana.banacash.API.ApiClient;
import com.xbanana.banacash.DAO.PinDAO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button login;
    EditText pin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        login = findViewById(R.id.btnLogin);
        pin = findViewById(R.id.pinMasuk);

        initAsset();

    }

    private void initAsset(){
        ProgressDialog progress = new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pin.getText().toString().length()!= 0)
                {
                    progress.setMessage("login process. . .");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setCancelable(false);
                    progress.show();

                    ApiInterface apInterface = ApiClient.getClient().create(ApiInterface.class);
                    Call<PinDAO> loginRequest = apInterface.loginRequest(
                            pin.getText().toString()
                    );
                    loginRequest.enqueue(new Callback<PinDAO>() {
                        @Override
                        public void onResponse(Call<PinDAO> call, Response<PinDAO> response) {
                            Intent i = new Intent(Login.this,MainMenu.class);
                            startActivity(i);
                            finish();
                            progress.dismiss();
                            Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<PinDAO> call, Throwable t) {
                            progress.dismiss();
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
