package com.example.android3fetchvideosfrompixabay.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3fetchvideosfrompixabay.network.network_model.Hits;
import com.example.android3fetchvideosfrompixabay.repository.PixabayRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PixabayViewModel extends ViewModel {
    public MutableLiveData<List<Hits>> videosList = new MutableLiveData<List<Hits>>();
    PixabayRepository repository;

    @Inject
    public PixabayViewModel(PixabayRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<Hits>> getVideos(String word) {
        videosList = repository.getVideos(word);
        return videosList;
    }
}
