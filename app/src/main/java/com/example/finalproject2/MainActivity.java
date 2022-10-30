package com.example.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = (Button) findViewById(R.id.login_btn);
        Button btnRegister = (Button) findViewById(R.id.register_btn);
        Button btnAdmin = (Button) findViewById(R.id.admin_login);
        Button btnStaff = (Button) findViewById(R.id.staff_login);
        Button btnAbout = (Button) findViewById(R.id.about);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnAdmin.setOnClickListener(this);
        btnStaff.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_btn) {
            startActivity(new Intent(getApplicationContext(), UserLogin.class));
        }
        else if (v.getId() == R.id.register_btn) {
            startActivity(new Intent(getApplicationContext(), UserRegister.class));
        }
        else if (v.getId() == R.id.admin_login) {
            startActivity(new Intent(getApplicationContext(), AdminLogin.class));
        }
        else if (v.getId() == R.id.staff_login) {
            startActivity(new Intent(getApplicationContext(), StaffLogin.class));
        }
        else if (v.getId() == R.id.about) {
            startActivity(new Intent(getApplicationContext(), About.class));
        }
    }
}