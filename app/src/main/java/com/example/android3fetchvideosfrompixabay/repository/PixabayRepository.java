package com.example.android3fetchvideosfrompixabay.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.android3fetchvideosfrompixabay.network.PixabayApi;
import com.example.android3fetchvideosfrompixabay.network.network_model.Hits;
import com.example.android3fetchvideosfrompixabay.network.network_model.PixabayResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixabayRepository {
    PixabayApi api;

    @Inject
    public PixabayRepository(PixabayApi api) {
        this.api = api;
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
}

