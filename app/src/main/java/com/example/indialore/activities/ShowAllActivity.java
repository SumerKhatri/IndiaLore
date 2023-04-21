package com.example.indialore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.indialore.R;
import com.example.indialore.adapters.ShowAllAdapter;
import com.example.indialore.models.CategoryModel;
import com.example.indialore.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {
    ShowAllAdapter showAllAdapter;
    RecyclerView recyclerView;
    List<ShowAllModel>showAllModelList;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        recyclerView=findViewById(R.id.show_all_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        showAllModelList=new ArrayList<>();

        showAllAdapter=new ShowAllAdapter(showAllModelList,getApplicationContext());
        recyclerView.setAdapter(showAllAdapter);
        db=FirebaseFirestore.getInstance();
        Object obj=getIntent().getSerializableExtra("detailed");
        if(obj == CategoryModel.class) {
            db.collection("Category")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    //  Log.d(TAG, document.getId() + " => " + document.getData());
                                    ShowAllModel showAllModel = document.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            } else {
                                // Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }else{
            db.collection("Products")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    //  Log.d(TAG, document.getId() + " => " + document.getData());
                                    ShowAllModel showAllModel = document.toObject(ShowAllModel.class);
                                    showAllModelList.add(showAllModel);
                                    showAllAdapter.notifyDataSetChanged();
                                }
                            } else {
                                // Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
    }
}