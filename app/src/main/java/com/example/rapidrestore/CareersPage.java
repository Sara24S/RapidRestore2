package com.example.rapidrestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CareersPage extends AppCompatActivity {

    ListView lv;
    Button buttonLogOut;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_careers_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
        buttonLogOut = findViewById(R.id.btnLogOut);

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(CareersPage.this, MainActivity.class));
                finish();
            }
        });

        lv=(ListView) findViewById(R.id.listView);
        int[] imageIDs = {R.drawable.plummer, R.drawable.woodworker, R.drawable.mechanic};
        //Rate
        int[] rateIDs = {R.drawable.star1, R.drawable.star1, R.drawable.star1};

        String[] names= {"plummer", "woodworker", "mechanic" };
        //Names
        String[] provNames= {"Ali Zayyat", "Ahmad Khodor", "Aya Aldor" };
        //countries
        String[] countries= {"Nabateye", "Tyre", "Beirut" };


        lv.setAdapter(new CustomAdapter(this, names, imageIDs, provNames, rateIDs, countries));
    }
}