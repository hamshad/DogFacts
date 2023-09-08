package com.example.cachingroom1;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceRepository {

    Context application;

    DogService service;
    public static final String BASE_URL = "https://dog-api.kinduff.com/api/";
    MutableLiveData<List<String>> fact = new MutableLiveData<>();

    public ServiceRepository(Context application) {
        this.application = application;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        service = retrofit.create(DogService.class);
        Log.d("TAG", "ServiceRepository: ");
    }

    public LiveData<List<String>> getFactData(int id) {

        service.getDogFacts(id)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DogFact>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {

                               }

                               @Override
                               public void onNext(@NonNull DogFact dogFact) {
                                   FactDatabase db = Room.databaseBuilder(application, FactDatabase.class, application.getString(R.string.app_name)).build();
                                   CachedData data = new CachedData();
                                   data.id = 1;
                                   data.facts = dogFact.toString(dogFact.getFact());
                                   db.cachedDataDao().insert(data);
                                   fact.postValue(dogFact.getFact());
                                   if (dogFact.getFact() == null) {
                                       Log.d("TAG", "Null DogFact");
                                   } else {
                                       Log.d("TAG111", "getFact: "+dogFact.toString(dogFact.getFact()));
                                   }
                               }

                               @Override
                               public void onError(@NonNull Throwable e) {
//                                   Toast.makeText(application, "Couldn't load!", Toast.LENGTH_SHORT).show();
                                   Log.d("TAG", "Couldn't Load");
                               }

                               @Override
                               public void onComplete() {
//                                   Toast.makeText(application, "Data Loaded", Toast.LENGTH_SHORT).show();
                                   Log.d("TAG", "Data Loaded");
                               }
                           }
                );
        return fact;
    }

}
