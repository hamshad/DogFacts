package com.example.cachingroom1;

import android.os.Bundle;
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

        ui.refreshLayout.setOnRefreshListener(this::onRefresh);
        adapter = new FactAdapter(viewModel.getCache());
        setRecycler();
    }

    private void setRecycler() {
        ui.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (viewModel.getCache() != null) {
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

    private void onRefresh() {
//        recreate();
        viewModel.fetchApi().observe(this, strings -> adapter.updateFactListItems(strings));
        ui.refreshLayout.setRefreshing(false);
    }
}