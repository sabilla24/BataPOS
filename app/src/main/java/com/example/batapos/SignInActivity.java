package com.example.batapos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mehdi.sakout.fancybuttons.FancyButton;

public class SignInActivity extends AppCompatActivity {

    private EditText mUsername,mPassword;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mUsername=findViewById(R.id.et_signin_admin);
        mPassword=findViewById(R.id.et_signin_password);
        mLogin=findViewById(R.id.btn_login);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {;

                if (mUsername.getText().toString().equals("kasir")&&mPassword.getText().toString().equals("143")){
                    //jika Login berhasil
                    Toast.makeText(getApplicationContext(), "LOGIN SUKSES",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    finish();
                } else {
                    //jika Login gagal
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
                    builder.setMessage("LOGIN GAGAL").setNegativeButton("Retry", null).create().show();
                }
            }
        });
    }
}
