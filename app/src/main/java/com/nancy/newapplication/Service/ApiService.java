package com.nancy.newapplication.Service;

import com.nancy.newapplication.Model.Flowers;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/feeds/flowers.json")
    Observable<List<Flowers>> getFlowers();
}
