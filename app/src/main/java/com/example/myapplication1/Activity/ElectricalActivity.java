package com.example.myapplication1.Activity;

import android.os.Bundle;

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

        EdgeToEdge.enable(this); // Edge-to-Edge UI

        initRecyclerView();

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
        items.add(new ElectricDomain("Vimukthi Edirisinha", "2 years", "eletrical", 3.5f, 7));
        items.add(new ElectricDomain("Manusha Lakshan", "2 years", "eletrical", 4.0f, 12));
        items.add(new ElectricDomain("Manoja Somarathna", "2 years", "eletrical", 4.8f, 20));

        // Set up RecyclerView using View Binding
        binding.elcViewList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ElectricAdapter adapterElectrical = new ElectricAdapter(items);
        binding.elcViewList.setAdapter(adapterElectrical);
    }
}
