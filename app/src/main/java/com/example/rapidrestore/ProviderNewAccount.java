package com.example.rapidrestore;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class ProviderNewAccount extends AppCompatActivity {

    TextInputEditText editTextNumberOrEmail, editTextPassword;
    Spinner spinnerRegions;
    String spinnerSelection;
    String experience;
    boolean checkedCarpenter, checkedElectrician, checkedRoofer, checkedGlassTechnican, checkedMason,
            checkedPlumber,checkedLocksmith;


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

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            experience = radioButton.getText().toString();
            Toast.makeText(this,experience , Toast.LENGTH_LONG).show();
            // Your logic here
            // DON'T call finish() here
        });


        checkedCarpenter = false;
        checkedElectrician = false;
        checkedLocksmith = false;
        checkedGlassTechnican = false;
        checkedMason = false;
        checkedRoofer = false;
        checkedPlumber = false;

        editTextNumberOrEmail = findViewById(R.id.edit_text_numberOrEmail);
        editTextPassword = findViewById(R.id.edit_text_password);
        spinnerRegions = findViewById(R.id.spinner_regions);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ProviderNewAccount.this,
                R.array.regions_array, android.R.layout.simple_spinner_dropdown_item);
        spinnerRegions.setAdapter(adapter);
        spinnerRegions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerSelection = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerSelection = "Nothing is selected";
            }
        });


    }

    public void submit(View view) {
        String NumOrEmail = String.valueOf(editTextNumberOrEmail.getText());
        String Password = String.valueOf(editTextPassword.getText());
        //save as in database as work request.
        //Admin accepts/rejects
        //worker receives notification with admin decision
        //if accepted, he can login with his account
        String t="your request will be reviewed\nresponse in next 24h";

        Toast.makeText(this, spinnerSelection, Toast.LENGTH_LONG).show();

    }


    public void checkBoxProfession(View view) {

        if (view.getId() == R.id.checkbox_carpenter)
            checkedCarpenter = ((CheckBox)view).isChecked();
        else if (view.getId() == R.id.checkbox_electrician)
            checkedElectrician = ((CheckBox)view).isChecked();
        else if (view.getId() == R.id.checkbox_locksmit)
            checkedLocksmith = ((CheckBox)view).isChecked();
        else if (view.getId() == R.id.checkbox_mason)
            checkedMason = ((CheckBox)view).isChecked();
        else if (view.getId() == R.id.checkbox_plumber)
            checkedPlumber = ((CheckBox)view).isChecked();
        else if (view.getId() == R.id.checkbox_glassTechnican)
            checkedGlassTechnican = ((CheckBox)view).isChecked();
        else if (view.getId() == R.id.checkbox_roofer)
            checkedRoofer = ((CheckBox)view).isChecked();
    }




}