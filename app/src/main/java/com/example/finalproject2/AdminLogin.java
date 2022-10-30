package com.example.finalproject2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AdminLogin extends AppCompatActivity implements View.OnClickListener {
    private EditText emailForm, passForm;
    private Button btnlogin;
    private FirebaseFirestore db;
    private String email, password;


    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        emailForm = findViewById(R.id.admin_username);
        passForm = findViewById(R.id.admin_pswd);
        btnlogin = findViewById(R.id.admin_login_btn);

        db = FirebaseFirestore.getInstance();
        btnlogin.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.admin_login_btn) {
            login();
        }
    }

    private void login() {
        email = emailForm.getText().toString();
        password = passForm.getText().toString();

        if (email.equals("")) {
            Toast.makeText(this, "please enter valid email", Toast.LENGTH_SHORT).show();
        }
        else if (password.equals("")) {
            Toast.makeText(this, "plese enter valid password", Toast.LENGTH_SHORT).show();
        }

        db.collection("admin").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        String a = doc.getString("email");
                        String b = doc.getString("password");

                        String a1 = email.trim();
                        String b1 = password.trim();

                        if (a.equalsIgnoreCase(a1) & b.equalsIgnoreCase(b1)) {
                            Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), AdminPage.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    };

                }
            }
        });

    }

    }

