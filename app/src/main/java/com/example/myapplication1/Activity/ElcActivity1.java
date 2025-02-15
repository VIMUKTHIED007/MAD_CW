package com.example.myapplication1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication1.R;
import com.example.myapplication1.databinding.ActivityElc1Binding;
import com.example.myapplication1.databinding.ActivityHomeBinding;

public class ElcActivity1 extends AppCompatActivity {
    ActivityElc1Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityElc1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElcActivity1.this, ElectricalActivity.class);
                startActivity(intent);
            }
        });

        Button btnBookNow = findViewById(R.id.btnBookNow);
        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the client name from the TextView
                String clientName = binding.clientName.getText().toString();

                // Create an Intent to navigate to CashPaymentActivity
                Intent intent = new Intent(ElcActivity1.this, CashPaymentActivity.class);

                // Pass the client name to the next activity
                intent.putExtra("CLIENT_NAME", clientName);

                // Start CashPaymentActivity
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