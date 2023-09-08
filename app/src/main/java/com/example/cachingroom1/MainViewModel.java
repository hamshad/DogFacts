package com.example.cachingroom1;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    List<String> factList = new ArrayList<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<String>> fetchApi() {
//        Log.d("TAG", "fetchApi: "+new ServiceRepository(getApplication()).getFactData(10));
        return new ServiceRepository(getApplication()).getFactData(10);
    }

    public List<String> getCache() {

        FactDatabase db = Room.databaseBuilder(getApplication(), FactDatabase.class, getApplication().getString(R.string.app_name)).allowMainThreadQueries().build();
        CachedDataDao dataDao = db.cachedDataDao();
        CachedData data = dataDao.getCachedFacts(1);
        if (data == null) {
            return null;
        }
        try {
            JSONObject fact = new JSONObject(data.facts);
            Iterator<String> x = fact.keys();
//            List<String> list = new ArrayList<>();
            while (x.hasNext()) {
//                list.add(x.next());
                factList.add(x.next());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return factList;
    }
}
