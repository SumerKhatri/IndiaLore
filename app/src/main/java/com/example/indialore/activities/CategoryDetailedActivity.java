package com.example.indialore.activities;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.indialore.R;
import com.example.indialore.adapters.CategoryDetailedAdapter;
import com.example.indialore.adapters.ProductAdapter;
import com.example.indialore.models.CategoryModel;
import com.example.indialore.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailedActivity extends AppCompatActivity {
    TextView cat_name;
    CategoryModel categoryModel;
    FirebaseFirestore db;
    List<ProductModel> productModelList;
    CategoryDetailedAdapter categoryDetailedAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detailed);
        Object object=getIntent().getSerializableExtra("detailed");
        if(object instanceof CategoryModel){
            categoryModel=(CategoryModel) object;
        }
        cat_name=findViewById(R.id.cat_name);
        cat_name.setText(categoryModel.getName());

        db=FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.cat_detailed_recView);
        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ProductModel productModel=document.toObject(ProductModel.class);
                                try {
                                    if (productModel.getState().toLowerCase().equals(categoryModel.getName().toLowerCase()))
                                        productModelList.add(productModel);
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