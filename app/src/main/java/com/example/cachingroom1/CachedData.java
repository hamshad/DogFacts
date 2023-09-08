package com.example.cachingroom1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dog_fact")
public class CachedData {

    @PrimaryKey
    int id;
    @ColumnInfo(name = "facts")
    String facts;

}
