package com.example.rapidrestore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneLogin extends AppCompatActivity {

    EditText editTextPhone, editTextOTP;
    Button buttonGenerateOTP, buttonVerifyOTP;
    FirebaseAuth mAuth;

    String codeID;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phone_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            mAuth.signOut();
        }

        editTextPhone = findViewById(R.id.phone);
        editTextOTP = findViewById(R.id.OTP);
        buttonGenerateOTP = findViewById(R.id.btnGenerateOTP);
        buttonVerifyOTP = findViewById(R.id.btnVerifyOTP);
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                codeID = s;
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                editTextOTP.setText(e.getMessage().toString());

                Toast.makeText(PhoneLogin.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        };

        buttonGenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextPhone.getText().toString().isEmpty())
                    Toast.makeText(PhoneLogin.this, "enter number", Toast.LENGTH_SHORT).show();
                else {
                    String number = editTextPhone.getText().toString();
                    sendOTP(number);
                    editTextOTP.setVisibility(View.VISIBLE);
                    buttonVerifyOTP.setVisibility(View.VISIBLE);
                    buttonGenerateOTP.setVisibility(View.GONE);
                }
            }
        });

        buttonVerifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextOTP.getText().toString().isEmpty())
                    Toast.makeText(PhoneLogin.this, "enter OTP", Toast.LENGTH_SHORT).show();
                else {
                    verifyOTP(editTextOTP.getText().toString());
                }
            }
        });
    }

    void sendOTP(String number){
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber("+961"+number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    void verifyOTP(String OTP){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeID, OTP);

        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                    Toast.makeText(PhoneLogin.this, mAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
                else {
                    editTextOTP.setText(task.getException().getMessage().toString());

                    Toast.makeText(PhoneLogin.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}