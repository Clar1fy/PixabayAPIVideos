package com.example.android3fetchvideosfrompixabay.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3fetchvideosfrompixabay.databinding.VideosHolderBinding;
import com.example.android3fetchvideosfrompixabay.network.network_model.responses.pixabay.Hits;

import java.util.ArrayList;
import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosHolder> {
    List<Hits> list = new ArrayList<>();

    @NonNull
    @Override
    public VideosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideosHolder(VideosHolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideosHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setApiData(List<Hits> list) {
        this.list = list;
    }

    public class VideosHolder extends RecyclerView.ViewHolder {
        private VideosHolderBinding binding;

        public VideosHolder(@NonNull VideosHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Hits hits) {
            binding.vvVideo.setVideoPath(hits.getUrl());

        }
    }
}
