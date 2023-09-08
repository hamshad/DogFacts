package com.example.cachingroom1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CachedDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CachedData data);

    @Query("SELECT * FROM dog_fact WHERE id = :id")
    CachedData getCachedFacts(int id);

    @Query("DELETE FROM dog_fact")
    void clearFacts();

}
