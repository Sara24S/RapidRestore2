package com.example.rapidrestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class HomeownerNewAccount extends AppCompatActivity {

    TextInputEditText editTextNumberOrEmail, editTextPassword;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homeowner_new_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNumberOrEmail = findViewById(R.id.edit_text_numberOrEmail);
        editTextPassword = findViewById(R.id.edit_text_password);

    }

    public void signUp(View view) {
        String name = "";
        String number = "";
        String email = "";
        String NumOrEmail = String.valueOf(editTextNumberOrEmail.getText());
        String Password = String.valueOf(editTextPassword.getText());
        if(NumOrEmail.contains("mail")){
            email = NumOrEmail;
        }
        else number = NumOrEmail;
        if (NumOrEmail.isEmpty() || Password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Empty fields!", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = "http://10.0.2.2/RapidRestore/adduser.php?name="
                + name + "&password=" + Password + "&email=" +
                email +"&phonenumber=" +number+ "&role=homeowner";
        RequestQueue queue = Volley.newRequestQueue(this);

        firebaseAuth.createUserWithEmailAndPassword(NumOrEmail,Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "SignUp Successful", Toast.LENGTH_SHORT).show();
                            StringRequest request = new StringRequest(
                                    Request.Method.POST, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(getApplicationContext(), response,
                                                    Toast.LENGTH_SHORT).show();
                                            editTextNumberOrEmail.setText("");
                                            editTextPassword.setText("");
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "Error:" +
                                            error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            queue.add(request);
                            Intent i = new Intent(HomeownerNewAccount.this, CareersPage.class);
                            startActivity(i);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}