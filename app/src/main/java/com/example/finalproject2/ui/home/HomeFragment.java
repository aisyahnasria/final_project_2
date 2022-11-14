package com.example.finalproject2.ui.home;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.finalproject2.About;
import com.example.finalproject2.ProductsDetails;
import com.example.finalproject2.R;
import com.example.finalproject2.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    Activity context;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;

    }

    @Override
    public void onStart(){
        super.onStart();
        ImageButton clothing = (ImageButton) context.findViewById(R.id.clothing_image);
        ImageButton book = (ImageButton) context.findViewById(R.id.book_image);
        ImageButton electronic = (ImageButton) context.findViewById(R.id.electronic_image);
        ImageButton other = (ImageButton) context.findViewById(R.id.other_image);

        clothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ProductsDetails.class));
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, ProductsDetails.class));

                startActivity(new Intent(context, ProductsDetails.class));

            }
        });

        electronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, ProductsDetails.class));

                startActivity(new Intent(context, About.class));

            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ProductsDetails.class));
                startActivity(new Intent(context, About.class));

            }
        });

    }


}