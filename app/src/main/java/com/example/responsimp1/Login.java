package com.example.responsimp1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private SharePrefManager sharePrefManager;
    private EditText etusername, etpassword;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharePrefManager = new SharePrefManager(this);
        etusername = findViewById(R.id.etusername);
        etpassword = findViewById(R.id.etpassword);
        btnlogin = findViewById(R.id.btnlogin);

        login();
    }

    private void login() {
        btnlogin.setOnClickListener(v -> {
            final String username = etusername.getText().toString();
            final String password = etpassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(Login.this, "Username dan Password Salah!!!", Toast.LENGTH_SHORT).show();
            } else {
                String spusername = sharePrefManager.getUsername();
                String sppassword = sharePrefManager.getPassword();

                Log.d("username", "user" + username);
                Log.d("password", "pass" + password);

                if (username.equals(spusername) && password.equals(sppassword)) {
                    Intent i = new Intent(Login.this, Profile.class);
                    sharePrefManager.saveIsLogin(true);
                    finishAffinity();
                    startActivity(i);
                } else {
                    Toast.makeText(Login.this, "Username dan Password Salah!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}