package com.example.finalproject2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserRegister extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "userRegister";
    private EditText username, pswd, noPhone;
    private Button register;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private String email, password, phone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        username = (EditText) findViewById(R.id.user_regist_username);
        pswd = (EditText) findViewById(R.id.user_regist_pswd);
        noPhone = (EditText) findViewById(R.id.user_no_phone);
        register = (Button) findViewById(R.id.user_regist_btn);

        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.user_regist_btn) {
            signUp();
        }

    }

    private void signUp() {
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        email = username.getText().toString();
        password = pswd.getText().toString();
        phone = noPhone.getText().toString();


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Data Staff berhasil dibuat", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), AdminPage.class));
                    addUser(email, password, phone);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Data Staff gagal dibuat", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void addUser(String email, String password, String phone) {
        CollectionReference dbUser = db.collection("user");

        User user = new User(email, password, phone);

        dbUser.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "success register your account", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed to register yout account", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Required");
            result = false;
        } else {
            username.setError(null);

        }
        if (TextUtils.isEmpty(pswd.getText().toString())) {
            pswd.setError("Required");
            result = false;
        } else {
            pswd.setError(null);
        }
        if (pswd.getText().length() < 6) {
            pswd.setError("try again!, use 6 character or number");
            result = false;
        } else {
            pswd.setError(null);
        }
        if (TextUtils.isEmpty(noPhone.getText().toString())) {
            noPhone.setError("Required");
            result = false;
        } else {
            pswd.setError(null);
        }

        return result;
    }

}