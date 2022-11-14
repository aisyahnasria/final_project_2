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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AdminAddStock extends AppCompatActivity {
    private EditText itemName, itemQty;
    private Button editBtn;
    private FirebaseFirestore db ;

    @Override
    protected void onCreate(@NonNull Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.admin_add_stock);

        itemName = (EditText) findViewById(R.id.item_id);
        itemQty = (EditText) findViewById(R.id.item_quantity);
        editBtn = (Button) findViewById(R.id.add_stok_btn);
        db = FirebaseFirestore.getInstance();

       editBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String productName = itemName.getText().toString();
               String strProductQty = itemQty.getText().toString();
               Integer productQty = new Integer(strProductQty).intValue();
               UpdateProduct(productName,productQty);
               itemName.setText("");
               itemQty.setText("");
           }

           private void UpdateProduct(String productName, Integer productQty) {
               db.collection("products")
                       .whereEqualTo("name", productName)
                       .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if (task.isSuccessful() && !task.getResult().isEmpty()) {
                           DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                           String documentID = documentSnapshot.getId();
                           db.collection("products")
                                   .document(documentID).update("Qty",productQty)
                                   .addOnSuccessListener(new OnSuccessListener<Void>() {
                                       @Override
                                       public void onSuccess(Void unused) {
                                           Toast.makeText(getApplicationContext(),"Success Updated",Toast.LENGTH_SHORT).show();
                                           startActivity(new Intent(getApplicationContext(), AdminPage.class));
                                       }
                                   }).addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Toast.makeText(getApplicationContext(),"Error, Try Again",Toast.LENGTH_SHORT).show();
                               }
                           });
                       } else {
                           Toast.makeText(AdminAddStock.this, "Failed", Toast.LENGTH_SHORT).show();
                       }
                   }
               });


           }
       });
}
}
