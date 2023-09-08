package com.example.cachingroom1;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DogService {

    @GET("facts")
    Observable<DogFact> getDogFacts(@Query("number") long num);

}
