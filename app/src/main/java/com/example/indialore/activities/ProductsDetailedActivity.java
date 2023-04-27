package com.example.indialore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.indialore.R;
import com.example.indialore.models.ProductModel;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProductsDetailedActivity extends AppCompatActivity {
    ImageView img,inc,dec;
    TextView name,price,description,quantity;
    RatingBar rating;
    Button addToCart,buyBtn;
    FirebaseFirestore firebaseFirestore;
    ProductModel productModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_detailed);
        img=findViewById(R.id.detailed_img);
        inc=findViewById(R.id.inc_quantity_btn);
        dec=findViewById(R.id.dec_quantity_btn);
        name=findViewById(R.id.product_name);
        price=findViewById(R.id.product_price);
        description=findViewById(R.id.product_description);
        description.setMovementMethod(new ScrollingMovementMethod());
        rating=findViewById(R.id.ratingBar);
        quantity=findViewById(R.id.product_quantity);

        firebaseFirestore=FirebaseFirestore.getInstance();

        Object object=getIntent().getSerializableExtra("detailed");
        if(object instanceof ProductModel){
            productModel=(ProductModel) object;
        }
        if(productModel!=null){
            Glide.with(getApplicationContext()).load(productModel.getImg_url()).into(img);
            name.setText(productModel.getName());
            price.setText(""+productModel.getPrice());
            description.setText(productModel.getDescription());
        }

        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int q=Integer.parseInt(String.valueOf(quantity.getText()));
                q++;
                quantity.setText(""+q);
            }
        });
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int q=Integer.parseInt(String.valueOf(quantity.getText()));
                q--;
                if(q<1)
                    q=1;
                quantity.setText(""+q);
            }
        });
    }
}