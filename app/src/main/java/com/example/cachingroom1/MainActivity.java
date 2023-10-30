package com.example.cachingroom1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.cachingroom1.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //https://api.dictionaryapi.dev/api/v2/entries/en/hello
    //https://dog-api.kinduff.com/api/facts?number=5

    ActivityMainBinding ui;
    MainViewModel viewModel;
    FactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        ui.refreshButton.setOnClickListener(this::onRefresh);
        ui.deleteButton.setOnClickListener(this::onDelete);

        adapter = new FactAdapter(viewModel.getCache());
        setRecycler();
    }

    private void setRecycler() {
        ui.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (adapter.list != null && !adapter.list.isEmpty()) {
            ui.recyclerView.setAdapter(adapter);
            Toast.makeText(getApplication(), "Data From Cache", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.fetchApi().observe(this, strings -> {
                adapter = new FactAdapter(strings);
                ui.recyclerView.setAdapter(adapter);
            });
            Toast.makeText(getApplication(), "Data From Api", Toast.LENGTH_SHORT).show();
        }
    }

    private void onRefresh(View view) {
//        viewModel.fetchApi().observe(this, strings -> adapter.updateFactListItems(strings));
        viewModel.fetchApi().observe(this, strings -> ui.recyclerView.setAdapter(new FactAdapter(strings)));
    }

    private void onDelete(View view) {
        viewModel.deleteDB();
    }

    @Override
    protected void onStop() {
        viewModel.closeDB();
        super.onStop();
    }
}