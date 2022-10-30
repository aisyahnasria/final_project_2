package com.example.finalproject2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminPage extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

        Button btnAddStaff = (Button) findViewById(R.id.admin_add_staff_btn);
        Button btnAddStock = (Button) findViewById(R.id.admin_add_stock_btn);

        btnAddStaff.setOnClickListener(this);
        btnAddStock.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.admin_add_staff_btn) {
            startActivity(new Intent(getApplicationContext(), AdminAddStaff.class));
        } else if (v.getId() == R.id.admin_add_stock_btn) {
            startActivity(new Intent(getApplicationContext(), AdminAddStock.class));

        }
    }
}
