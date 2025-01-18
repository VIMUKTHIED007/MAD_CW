package com.example.myapplication1.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication1.Domain.ElectricDomain;
import com.example.myapplication1.databinding.ElectriListBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ElectricAdapter extends RecyclerView.Adapter<ElectricAdapter.ViewHolder> {
    private final ArrayList<ElectricDomain> items;

    public ElectricAdapter(ArrayList<ElectricDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ElectriListBinding binding = ElectriListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ElectricDomain currentItem = items.get(position);

        // Bind data to views
        holder.binding.name.setText(currentItem.getName());
        holder.binding.experience.setText(currentItem.getExperience());
        holder.binding.ratingBar.setRating(currentItem.getRating());
        holder.binding.ratingCount.setText("(" + currentItem.getRatingCount() + ")");

        // Load image using Glide
        int drawableResourceId = holder.binding.getRoot().getResources()
                .getIdentifier(currentItem.getPicPath(), "drawable", holder.binding.getRoot().getContext().getPackageName());
        Glide.with(holder.binding.getRoot().getContext())
                .load(drawableResourceId)
                .into(holder.binding.elcImage);

        // Handle RatingBar changes
        holder.binding.ratingBar.setOnRatingBarChangeListener((RatingBar ratingBar, float rating, boolean fromUser) -> {
            if (fromUser) {
                // Update the rating for the item
                currentItem.setRating(rating);

                // Optional: Sort the list by ratings
                items.sort(Comparator.comparingDouble(ElectricDomain::getRating).reversed());

                // Notify the adapter of the changes
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ElectriListBinding binding;

        public ViewHolder(@NonNull ElectriListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
