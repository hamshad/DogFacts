package com.example.cachingroom1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {CachedData.class},
        version = 1
)
public abstract class FactDatabase extends RoomDatabase {
    public abstract CachedDataDao cachedDataDao();
}
