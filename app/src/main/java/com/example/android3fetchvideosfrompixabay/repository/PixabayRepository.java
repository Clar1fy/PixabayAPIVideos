package com.example.android3fetchvideosfrompixabay.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.android3fetchvideosfrompixabay.network.apis.PixabayApi;
import com.example.android3fetchvideosfrompixabay.network.apis.RapidApi;
import com.example.android3fetchvideosfrompixabay.network.network_model.responses.pixabay.Hits;
import com.example.android3fetchvideosfrompixabay.network.network_model.responses.pixabay.PixabayResponse;
import com.example.android3fetchvideosfrompixabay.network.network_model.responses.rapid.RapidHits;
import com.example.android3fetchvideosfrompixabay.network.network_model.responses.rapid.RapidResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixabayRepository {
    PixabayApi api;
    RapidApi rapidApi;

    @Inject
    public PixabayRepository(PixabayApi api, RapidApi rapidApi) {
        this.api = api;
        this.rapidApi = rapidApi;
    }

    public MutableLiveData<List<Hits>> getVideos(String word) {
        MutableLiveData<List<Hits>> videosList = new MutableLiveData<List<Hits>>();

        api.getVideos(word).enqueue(new Callback<PixabayResponse>() {
            @Override
            public void onResponse(Call<PixabayResponse> call, Response<PixabayResponse> response) {
                if (response.isSuccessful()) {
                    videosList.postValue(response.body().getHits());
                }

            }

            @Override
            public void onFailure(Call<PixabayResponse> call, Throwable t) {

            }
        });
        return videosList;
    }

    public MutableLiveData<List<RapidHits>> getTranslation(String word) {
        MutableLiveData<List<RapidHits>> translationsList = new MutableLiveData<>();

        rapidApi.getTranslation(word, 1, 0, "translated-mymemory---translation-memory.p.rapidapi.com", "7fdf0c3215msh6a008d2bac47dfep1d5367jsne7b94b60412b").enqueue(new Callback<RapidResponse>() {
            @Override
            public void onResponse(Call<RapidResponse> call, Response<RapidResponse> response) {
                if (response.isSuccessful()) {
                    translationsList.postValue(response.body().getMatch());


                }
            }

            @Override
            public void onFailure(Call<RapidResponse> call, Throwable t) {

            }
        });
        return translationsList;
    }


}

