package com.example.myapplication1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication1.Adapter.ElectricAdapter;
import com.example.myapplication1.Domain.ElectricDomain;
import com.example.myapplication1.databinding.ActivityElectricalBinding;

import java.util.ArrayList;

public class ElectricalActivity extends AppCompatActivity {

    private ActivityElectricalBinding binding; // View Binding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ActivityElectricalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this); // Enable Edge-to-Edge UI

        initRecyclerView();

        binding.imgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElectricalActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        binding.imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElectricalActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElectricalActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        binding.imgMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElectricalActivity.this, MailsActivity.class);
                startActivity(intent);
            }
        });

        binding.imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElectricalActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });


        // Handle window insets for system bars padding
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }




    private void initRecyclerView() {
        // Create a list of ElectricDomain objects
        ArrayList<ElectricDomain> items = new ArrayList<>();
        items.add(new ElectricDomain("Saman Edirisnha", "3 years", "eletrical", 4.8f, 12));
        items.add(new ElectricDomain("Anjana Electrical", "1 years", "eletrical", 4.0f, 7));
        items.add(new ElectricDomain("Rathnayka Electrical", "1 years", "eletrical", 3.8f, 7));

        // Set up RecyclerView using View Binding
        binding.elcViewList.setLayoutManager(new LinearLayoutManager(this));
        ElectricAdapter adapter = new ElectricAdapter(items, new ElectricAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ElectricDomain item) {
                // Start the next page (ElcActivity1) and pass the clicked item data
                Intent intent = new Intent(ElectricalActivity.this, ElcActivity1.class);
                intent.putExtra("name", item.getName());
                intent.putExtra("experience", item.getExperience());
                intent.putExtra("rating", item.getRating());
                intent.putExtra("ratingCount", item.getRatingCount());
                startActivity(intent);
            }
        });




        binding.elcViewList.setAdapter(adapter);
    }
}
