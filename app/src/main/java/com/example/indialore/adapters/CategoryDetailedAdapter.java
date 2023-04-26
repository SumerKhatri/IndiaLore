package com.example.indialore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.indialore.R;
import com.example.indialore.activities.ProductsDetailedActivity;
import com.example.indialore.models.ProductModel;

import java.util.List;

public class CategoryDetailedAdapter extends RecyclerView.Adapter<CategoryDetailedAdapter.ViewHolder> {
    List<ProductModel> list;
    Context context;

    public CategoryDetailedAdapter(List<ProductModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryDetailedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.category_detailed_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryDetailedAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.price.setText(""+list.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ProductsDetailedActivity.class).putExtra("detailed",list.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.p_name);
            price=itemView.findViewById(R.id.p_price);
            imageView=itemView.findViewById(R.id.p_img);
        }
    }
}
