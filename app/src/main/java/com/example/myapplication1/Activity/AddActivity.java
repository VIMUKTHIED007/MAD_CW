package com.example.myapplication1.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.R;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    private EditText nameInput, typeInput, descriptionInput, numberInput;
    private Button submitButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db = FirebaseFirestore.getInstance();

        nameInput = findViewById(R.id.Addname);
        typeInput = findViewById(R.id.Addtype);
        descriptionInput = findViewById(R.id.adddescription);
        numberInput = findViewById(R.id.adddnumber);
        submitButton = findViewById(R.id.btnsignapp);

        submitButton.setOnClickListener(v -> saveToFirestore());
    }

    private void saveToFirestore() {
        String name = nameInput.getText().toString();
        String category = typeInput.getText().toString();
        String description = descriptionInput.getText().toString();
        String mobile = numberInput.getText().toString();

        if (name.isEmpty() || category.isEmpty() || description.isEmpty() || mobile.isEmpty()) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> client = new HashMap<>();
        client.put("name", name);
        client.put("category", category);
        client.put("description", description);
        client.put("mobile", mobile);

        db.collection("client")
                .add(client)
                .addOnSuccessListener(documentReference ->
                        Toast.makeText(this, "Data Added Successfully!", Toast.LENGTH_SHORT).show()
                )
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}
