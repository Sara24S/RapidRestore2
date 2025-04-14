package com.example.rapidrestore;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CareersPage extends AppCompatActivity {

    ListView lv;

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