package com.example.cachingroom1;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FactsDiffCallback extends DiffUtil.Callback {

    List<String> oldFacts;
    List<String> newFacts;

    public FactsDiffCallback(List<String> oldFacts, List<String> newFacts) {
        this.oldFacts = oldFacts;
        this.newFacts = newFacts;
    }

    @Override
    public int getOldListSize() {
        return oldFacts.size();
    }

    @Override
    public int getNewListSize() {
        return newFacts.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldFacts.get(oldItemPosition), newFacts.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldFacts.get(oldItemPosition), newFacts.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
