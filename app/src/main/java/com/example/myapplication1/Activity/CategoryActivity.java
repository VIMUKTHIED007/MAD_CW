package com.example.myapplication1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication1.Domain.ListDomain;
import com.example.myapplication1.R;
import com.example.myapplication1.databinding.ActivityCategoryBinding;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    ActivityCategoryBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListDomain> domainArrayList = new ArrayList<>();
    ListDomain listDomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

      int[] imageList = {R.drawable.electr_icon,R.drawable.plumb_icon,R.drawable.food_icon,R.drawable.carpent_icon,R.drawable.havey_icon};
      String[] nameList = {"Electrical","Plumber","Food Order","Carpenter","Haevy Duty"};

      for (int i = 0;i<imageList.length;i++){
          listDomain = new ListDomain(nameList[i],imageList[i]);
          domainArrayList.add(listDomain);
      }
      listAdapter = new com.example.myapplication1.Adapter.ListAdapter(CategoryActivity.this,domainArrayList);
      binding.ListView.setAdapter(listAdapter);
      binding.ListView.setClickable(true);

        // Set click listener for ListView items
        binding.ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0: // Electrical
                        intent = new Intent(CategoryActivity.this, ElectricalActivity.class);
                        break;
                    case 1: // Plumber
                        intent = new Intent(CategoryActivity.this, ElectricalActivity.class);
                        break;
                    case 2: // Food Order
                        intent = new Intent(CategoryActivity.this, ElectricalActivity.class);
                        break;
                    case 3: // Carpenter
                        intent = new Intent(CategoryActivity.this, ElectricalActivity.class);
                        break;
                    default: // Default case if needed
                        intent = new Intent(CategoryActivity.this, HomeActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });



        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });




        binding.imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        binding.imgMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, MailsActivity.class);
                startActivity(intent);
            }
        });

        binding.imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}