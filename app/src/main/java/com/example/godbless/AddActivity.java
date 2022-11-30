package com.example.godbless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText binNo, fillLevel, latitude, longitude;
    Button btnAdd, btnBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().hide();

        binNo = (EditText)findViewById(R.id.txtBinNo);
        fillLevel = (EditText)findViewById(R.id.txtFillLevel);
        latitude = (EditText)findViewById(R.id.txtLatitude);
        longitude = (EditText)findViewById(R.id.txtLongitude);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

        EditText fillLevel = (EditText) findViewById(R.id.txtFillLevel);
        fillLevel.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "100")});


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             insertData();
             clearAll();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    private void insertData() {

        Map<String,Object> map = new HashMap<>();
        map.put("bin_number",binNo.getText().toString());
        map.put("fill_level",fillLevel.getText().toString());
        map.put("latitude",latitude.getText().toString());
        map.put("longitude",longitude.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("Bins").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Bin Added Successfully.",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this,"Error while Adding.",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void clearAll()
    {
        binNo.setText("");
        fillLevel.setText("");
        latitude.setText("");
        longitude.setText("");
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}