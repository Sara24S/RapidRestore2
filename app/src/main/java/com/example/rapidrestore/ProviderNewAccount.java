package com.example.rapidrestore;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProviderNewAccount extends AppCompatActivity {

    EditText editTextEmail, editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_provider_new_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextEmail = findViewById(R.id.email);
        editTextPass = findViewById(R.id.password);
    }

    public void submit(View view) {
        String email = editTextEmail.getText().toString();
        String pass = editTextPass.getText().toString();
        //save as in database as work request.
        //Admin accepts/rejects
        //worker receives notification with admin decision
        //if accepted, he can login with his account
        String t="your request will be reviewed\nresponse in next 24h";

        Toast.makeText(this, t, Toast.LENGTH_LONG).show();

    }
}