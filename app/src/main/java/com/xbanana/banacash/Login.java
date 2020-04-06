package com.xbanana.banacash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,MainMenu.class);
                startActivity(i);
                finish();
            }
        });
    }

}
