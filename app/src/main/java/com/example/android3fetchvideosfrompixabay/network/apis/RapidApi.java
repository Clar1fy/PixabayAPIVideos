package com.example.android3fetchvideosfrompixabay.network.apis;

import com.example.android3fetchvideosfrompixabay.network.network_model.responses.rapid.RapidResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RapidApi {
    @GET("/api/get?langpair=en%7Cru&")
    Call<RapidResponse> getTranslation(@Query("q") String word,
                                       @Query("mt") int mt,
                                       @Query("onlyprivate") int onlyPrivate,
                                       @Header("x-rapidapi-host") String host,
                                       @Header("x-rapidapi-key") String key);
}

