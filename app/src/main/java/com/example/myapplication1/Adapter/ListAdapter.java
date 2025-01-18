package com.example.myapplication1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication1.Domain.ListDomain;
import com.example.myapplication1.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListDomain> {
    public ListAdapter(@NonNull Context context, ArrayList<ListDomain> dataArrayList) {
        super(context, R.layout.cate_list,dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
       ListDomain listData = getItem(position);

       if (view == null){
           view = LayoutInflater.from(getContext()).inflate(R.layout.cate_list,parent,false);
       }

        ImageView listImage = view.findViewById(R.id.listImage);
        TextView listName = view.findViewById(R.id.Name);

        listImage.setImageResource(listData.image);
        listName.setText(listData.name);

        return view;
    }
}
