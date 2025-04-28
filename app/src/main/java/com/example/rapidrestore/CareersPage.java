package com.example.rapidrestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CareersPage extends AppCompatActivity {

    private static final String URL_PRODUCTS = "http://192.168.242.1/RRmobile/Api.php";

    //a list to store all the products
    List<Provider> providerList;

    //the recyclerview
    RecyclerView recyclerView;

    Button buttonLogOut;
    FirebaseAuth mAuth;

    JSONArray data;

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

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        providerList = new ArrayList<>();
        loadProducts();

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
    }
    private void loadProducts(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {
                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                //adding the product to product list
                                providerList.add(new Provider(
                                        product.getInt("id"),
                                        product.getString("name"),
                                        product.getString("profession"),
                                        product.getDouble("rating"),
                                        product.getDouble("costperhour"),
                                        product.getString("region"),
                                        product.getString("profileimage")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            ProviderAdapter adapter = new ProviderAdapter(CareersPage.this, providerList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            Toast.makeText(CareersPage.this, e.toString(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        queue.add(stringRequest);

    }


}