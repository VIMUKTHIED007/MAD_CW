package com.example.myapplication1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication1.Domain.ElectricDomain;
import com.example.myapplication1.databinding.ElectriListBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ElectricAdapter extends RecyclerView.Adapter<ElectricAdapter.ViewHolder> {
    private ArrayList<ElectricDomain> items; // Data list
    private Context context;
    private OnItemClickListener listener;

    // Constructor with listener
    public ElectricAdapter(ArrayList<ElectricDomain> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ElectriListBinding binding = ElectriListBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ElectricDomain currentItem = items.get(position);

        // Bind the data
        holder.binding.name.setText(currentItem.getName());
        holder.binding.experience.setText(currentItem.getExperience());
        holder.binding.ratingBar.setRating(currentItem.getRating());
        holder.binding.ratingCount.setText("(" + currentItem.getRatingCount() + ")");

        // Add listener for the item click (null check for listener)
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(currentItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ElectriListBinding binding; // View Binding for item layout

        public ViewHolder(@NonNull ElectriListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ElectricDomain item);
    }
}
