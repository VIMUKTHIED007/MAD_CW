package com.example.myapplication1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.myapplication1.Domain.categoryDomain;
import com.example.myapplication1.databinding.ViewholderCateListBinding;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<categoryDomain> items;
    Context context;
    ViewholderCateListBinding binding;

    public CategoryAdapter(ArrayList<categoryDomain> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=ViewholderCateListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
       context= parent.getContext();
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
    binding.txtTitle.setText(items.get(position).getTitle());

    int drawableResourced=holder.itemView.getResources().getIdentifier(items.get(position).getPicPath()
            ,"drawable",holder.itemView.getContext().getPackageName());

        Glide .with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(binding.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ViewholderCateListBinding binding) {
            super(binding.getRoot());

        }
    }
}
