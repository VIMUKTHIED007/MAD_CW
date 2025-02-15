package com.example.myapplication1.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.Domain.BookingModel;
import com.example.myapplication1.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MailsActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private RecyclerView recyclerView;
    private com.example.myapplication1.Activity.BookingAdapter bookingAdapter;
    private List<BookingModel> bookingList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mails);

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookingList = new ArrayList<>();
        bookingAdapter = new com.example.myapplication1.Activity.BookingAdapter(bookingList);
        recyclerView.setAdapter(bookingAdapter);

        loadBookingsFromFirestore();
    }

    private void loadBookingsFromFirestore() {
        firestore.collection("bookings")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        bookingList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            BookingModel booking = document.toObject(BookingModel.class);
                            bookingList.add(booking);
                        }
                        bookingAdapter.notifyDataSetChanged();
                    } else {
                        Log.e("Firestore Error", "Error fetching bookings", task.getException());
                    }
                });
    }
}
