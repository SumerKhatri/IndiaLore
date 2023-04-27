package com.example.indialore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.indialore.R;
import com.example.indialore.adapters.CategoryDetailedAdapter;
import com.example.indialore.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<ProductModel>productModelList;
    CategoryDetailedAdapter categoryDetailedAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        String query=getIntent().getStringExtra("searchQuery");
        TextView tv=findViewById(R.id.textView3);
        tv.setText(query);

        db=FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.search_recView);
        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                try {
                                    ProductModel productModel=document.toObject(ProductModel.class);
//                                    if (productModel.getState().toLowerCase().equals(categoryModel.getName().toLowerCase()))
//                                        productModelList.add(productModel);
                                     if(productModel.getName().toLowerCase().contains(query.toLowerCase())){
                                         productModelList.add(productModel);
                                     }
                                }catch (Exception e){

                                }
                                categoryDetailedAdapter.notifyDataSetChanged();
                            }

                        } else {

                        }
                    }
                });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productModelList=new ArrayList<>();
        categoryDetailedAdapter=new CategoryDetailedAdapter(productModelList,this);
        recyclerView.setAdapter(categoryDetailedAdapter);

    }

}