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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AdminAddStaff extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "addStaff";
    private EditText username, pswd, confPass;
    private Button register;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private String email, password;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_staff);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        username = (EditText) findViewById(R.id.staff_register_username);
        pswd = (EditText) findViewById(R.id.staff_register_pswd);
        confPass = (EditText) findViewById(R.id.staff_confirm_pswd);
        register = (Button) findViewById(R.id.register_staff);


        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.register_staff) {
            signUp();
        }


    }
    
    private void signUp(){
        Log.d(TAG,"signUp");
        if (!validateForm()) {
            return;
        }

        email = username.getText().toString();
        password = pswd.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Data Staff berhasil dibuat", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), AdminPage.class));
                    addStaff(email, password);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Data Staff gagal dibuat", Toast.LENGTH_SHORT).show();
                }
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
        }
        else{
            pswd.setError(null);
        }
        if (pswd.getText().length() < 6) {
            pswd.setError("try again!, use 6 character or number");
            result = false;
        }
        else {
            pswd.setError(null);
        }
        if (TextUtils.isEmpty(confPass.getText().toString())){
            confPass.setError("Required");
            result = false;
        } else {
            confPass.setError(null);
        }
        if (!confPass.getText().toString().equals(pswd.getText().toString())){
            confPass.setError("try again");
            Toast.makeText(getApplicationContext(),"confirm password wrong, try again", Toast.LENGTH_SHORT).show();
            result = false;
        }else {
            confPass.setError(null);
        }
        return result;
    }

    private void addStaff(String email, String password) {
        CollectionReference dbUser = db.collection("staff");

        Staff staff = new Staff(email, password);

        dbUser.add(staff).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Staff account has been added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed to add staff account", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
