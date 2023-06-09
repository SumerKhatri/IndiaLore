package com.example.indialore.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.indialore.R;
import com.example.indialore.activities.ProductsDetailedActivity;
import com.example.indialore.activities.ShowAllActivity;
import com.example.indialore.adapters.CategoryAdapter;
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

public class HomeFragment extends Fragment {
    LinearLayout linearLayout;
    RecyclerView catRecyclerView,productRecyclerView;
    TextView catShowAll,productShowAll;
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;
    List<CategoryModel>categoryModelList;
    List<ProductModel>productModelList;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_home, container, false);

        productRecyclerView=root.findViewById(R.id.new_product_rec);
        catRecyclerView=root.findViewById(R.id.rec_category);

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Welcome");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        db=FirebaseFirestore.getInstance();
        linearLayout=root.findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.INVISIBLE);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel=document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                            }
                            progressDialog.dismiss();
                            linearLayout.setVisibility(View.VISIBLE);
                        } else {

                        }
                    }
                });

        catRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList=new ArrayList<>();
        categoryAdapter=new CategoryAdapter(getContext(),categoryModelList);
        catRecyclerView.setAdapter(categoryAdapter);

        //Products
        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                try {
                                    ProductModel productModel = document.toObject(ProductModel.class);
                                    productModelList.add(productModel);
                                    productAdapter.notifyDataSetChanged();
                                }catch (Exception e){

                                }
                            }
                        } else {

                        }
                    }
                });

        productRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        productModelList=new ArrayList<>();
        productAdapter=new ProductAdapter(getContext(),productModelList);
        productRecyclerView.setAdapter(productAdapter);


        //ImgSlider
        ImageSlider imageSlider=root.findViewById(R.id.image_slider);
        List<SlideModel>slideModels=new ArrayList<SlideModel>();

        slideModels.add(new SlideModel(R.drawable.banner1,"Discount on new products", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,"Quality Products", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3,"Affordable Prices", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels);
        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                startActivity(new Intent(getContext(), ShowAllActivity.class));
            }
        });


        catShowAll=root.findViewById(R.id.category_see_all);
        productShowAll=root.findViewById(R.id.newProducts_see_all);
        catShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ShowAllActivity.class).putExtra("detailed",CategoryModel.class));
            }
        });
        productShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),ShowAllActivity.class));
            }
        });
        return root;
    }

}