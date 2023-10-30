package com.example.cachingroom1;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cachingroom1.databinding.FactItemBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class FactAdapter extends RecyclerView.Adapter<FactAdapter.VH> {

    List<String> list;

    public FactAdapter(List<String> list) {
        if (list == null)
            this.list = new ArrayList<>();
        else
            this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.fact_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        h.ui.fact.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateFactListItems(List<String> newFacts) {
        FactsDiffCallback diffCallback = new FactsDiffCallback(list, newFacts);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            list.clear();
            list.addAll(newFacts);
            diffResult.dispatchUpdatesTo(this);
    }

    public static class VH extends RecyclerView.ViewHolder {

        FactItemBinding ui;

        public VH(@NonNull View itemView) {
            super(itemView);
            ui = FactItemBinding.bind(itemView);
        }
    }
}
