package com.example.finalproject2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProductsDetails extends AppCompatActivity {
    private RecyclerView productsRv;
    private ArrayList<Products> productsArrayList;
    private  FirestoreRecyclerAdapter firestoreRecyclerAdapter;
    private FirebaseFirestore db;
    ProgressBar loadingPB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_page);

        productsRv = findViewById(R.id.RV);
        loadingPB = findViewById(R.id.idProgressBar);

        db = FirebaseFirestore.getInstance();

        productsArrayList = new ArrayList<>();

        productsRv.setHasFixedSize(true);
        productsRv.setLayoutManager(new LinearLayoutManager(this));

        firestoreRecyclerAdapter = new FirestoreRecyclerAdapter(productsArrayList, this);
        productsRv.setAdapter(firestoreRecyclerAdapter);



        db.collection("products").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            loadingPB.setVisibility(View.GONE);
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list){
                                Products p = d.toObject(Products.class);
                                p.setId(d.getId());
                                productsArrayList.add(p);

                            }
                            firestoreRecyclerAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(ProductsDetails.this, "No Data Found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProductsDetails.this, "Fail to get the data." + e, Toast.LENGTH_SHORT).show();
            }
        });



    }


}
