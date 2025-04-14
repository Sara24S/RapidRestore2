package com.example.rapidrestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    EditText editTextEmail, editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
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
        //check in the database if they are right
        if(email.equals("Amal@gmail.com") && pass.equals("123")){
            Intent i = new Intent(this, CareersPage.class);
            startActivity(i);
        }
        else{
            String t="Wrong email or password";
            Toast.makeText(this, t, Toast.LENGTH_LONG).show();
        }
    }
}