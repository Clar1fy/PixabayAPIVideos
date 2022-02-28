package com.example.android3fetchvideosfrompixabay.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.android3fetchvideosfrompixabay.adapter.VideosAdapter;
import com.example.android3fetchvideosfrompixabay.base.BaseFragment;
import com.example.android3fetchvideosfrompixabay.databinding.FragmentSearchVideoBinding;
import com.example.android3fetchvideosfrompixabay.viewmodel.PixabayViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchVideoFragment extends BaseFragment<FragmentSearchVideoBinding> {
    PixabayViewModel viewModel;
    Handler handler;
    VideosAdapter adapter;


    @Override
    public FragmentSearchVideoBinding bind() {
        return FragmentSearchVideoBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PixabayViewModel.class);
        initAdapter();
        initListeners();

    }

    private void initListeners() {
        binding.etVideos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (handler != null) {
                    handler = null;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.progressbar.setVisibility(View.VISIBLE);
                        String word = binding.etVideos.getText().toString();
                        viewModel.getVideos(word).observe(getViewLifecycleOwner(), videos -> {
                            if (videos != null) {
                                binding.progressbar.setVisibility(View.INVISIBLE);
                                adapter.setApiData(videos);
                                binding.recyclerview.setAdapter(adapter);

                            }


                        });


                    }
                }, 2000);

            }
        });
    }

    private void initAdapter() {
        adapter = new VideosAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}