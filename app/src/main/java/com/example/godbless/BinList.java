package com.example.godbless;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class BinList extends AppCompatActivity {

    RecyclerView recyclerView;
    BinAdapter binAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bin_list);
        getSupportActionBar().setTitle("List of Bins");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView =(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<BinModel> options =
                new FirebaseRecyclerOptions.Builder<BinModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Bins"), BinModel.class)
                        .build();

        binAdapter = new BinAdapter(options);
        recyclerView.setAdapter(binAdapter);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        binAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        binAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<BinModel> options =
                new FirebaseRecyclerOptions.Builder<BinModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Bins").orderByChild("bin_number").startAt(str).endAt(str+"~"), BinModel.class)
                        .build();

        binAdapter = new BinAdapter(options);
        binAdapter.startListening();
        recyclerView.setAdapter(binAdapter);
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}