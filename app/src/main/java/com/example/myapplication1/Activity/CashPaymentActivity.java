package com.example.myapplication1.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication1.R;

import java.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;



public class CashPaymentActivity extends AppCompatActivity {
    private EditText descriptionInput;
    private CalendarView calendarView;
    private Button btnBookNow;
    private String selectedDate = "";

    private static String confirmedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cash_payment);

        // UI Components Initialize
        descriptionInput = findViewById(R.id.descriptionInput);
        calendarView = findViewById(R.id.calendarView2);
        btnBookNow = findViewById(R.id.btnBookNow);

        // Set Default Date (Current Date)
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        selectedDate = sdf.format(calendar.getTime());

        // Calendar View Date Change Listener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
            }
        });

        // Confirm Button Click
        btnBookNow.setOnClickListener(view -> {
            if (confirmedDate.equals(selectedDate)) {
                // If the date is already confirmed, show a message
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

            // Display Confirmation Message
            Toast.makeText(CashPaymentActivity.this, "Job Confirmed!\nDate: " + selectedDate + "\nDescription: " + description, Toast.LENGTH_LONG).show();
        });
    }
}
