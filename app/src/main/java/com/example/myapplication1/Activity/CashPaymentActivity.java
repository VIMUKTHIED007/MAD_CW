package com.example.myapplication1.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CashPaymentActivity extends AppCompatActivity {
    private EditText descriptionInput;
    private CalendarView calendarView;
    private Button btnBookNow;
    private String selectedDate = "";
    private static String confirmedDate = "";

    // TextView to display the client name
    private TextView clientNameTextView;

    // Firestore Database Instance
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);  // Initialize Firebase
        setContentView(R.layout.activity_cash_payment);

        // Initialize UI components
        descriptionInput = findViewById(R.id.descriptionInput);
        calendarView = findViewById(R.id.calendarView2);
        btnBookNow = findViewById(R.id.btnBookNow);
        clientNameTextView = findViewById(R.id.clientNameTextView);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        // Get the client name from Intent
        String clientName = getIntent().getStringExtra("CLIENT_NAME");

        // Display client name in TextView
        if (clientName != null) {
            clientNameTextView.setText(clientName);
        }

        // Set Default Date (Current Date)
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        selectedDate = sdf.format(calendar.getTime());

        // Calendar View Date Change Listener
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
        });

        // "Book Now" Button Click Event
        btnBookNow.setOnClickListener(view -> {
            if (confirmedDate.equals(selectedDate)) {
                Toast.makeText(CashPaymentActivity.this, "This date is already confirmed!", Toast.LENGTH_SHORT).show();
                return;
            }

            String description = descriptionInput.getText().toString().trim();

            if (description.isEmpty()) {
                Toast.makeText(CashPaymentActivity.this, "Please enter a description!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Confirm the date and description
            confirmedDate = selectedDate;

            // Save to Firestore Database
            saveBookingToFirestore(clientName, selectedDate, description);
        });
    }

    private void saveBookingToFirestore(String clientName, String date, String description) {
        // Create a booking object
        Map<String, Object> booking = new HashMap<>();
        booking.put("clientName", clientName);
        booking.put("date", date);
        booking.put("description", description);

        // Save data to Firestore
        firestore.collection("bookings")
                .add(booking)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(CashPaymentActivity.this, "Booking saved successfully!", Toast.LENGTH_SHORT).show();
                    Log.d("Firestore", "Booking saved with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(CashPaymentActivity.this, "Failed to save booking", Toast.LENGTH_SHORT).show();
                    Log.e("Firestore Error", e.getMessage());
                });
    }
}
