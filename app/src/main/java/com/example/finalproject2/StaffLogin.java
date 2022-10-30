package com.example.finalproject2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class StaffLogin extends AppCompatActivity implements View.OnClickListener {
    private EditText username, pswd;
    private Button loginButton;
    private FirebaseFirestore db;
    private String email, password;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_login);
        db = FirebaseFirestore.getInstance();


        username = (EditText) findViewById(R.id.staff_login_username);
        pswd = (EditText) findViewById(R.id.staff_login_pswd);
        loginButton = (Button) findViewById(R.id.staff_login_btn);

        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.staff_login_btn) {
            login();
        }

    }

    private void login() {
        email = username.getText().toString();
        password = pswd.getText().toString();

        if (email.equals("")) {
            Toast.makeText(this, "please enter valid email", Toast.LENGTH_SHORT).show();
        }
        else if (password.equals("")) {
            Toast.makeText(this, "plese enter valid password", Toast.LENGTH_SHORT).show();
        }

        db.collection("staff").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        String a = doc.getString("email");
                        String b = doc.getString("password");

                        String a1 = email.trim();
                        String b1 = password.trim();

                        if (a.equalsIgnoreCase(a1) & b.equalsIgnoreCase(b1)) {
                            Toast.makeText(StaffLogin.this, "Logged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), About.class));
                        }
                        else {
                            Toast.makeText(StaffLogin.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    };

                }
            }
        });

        }
    }
