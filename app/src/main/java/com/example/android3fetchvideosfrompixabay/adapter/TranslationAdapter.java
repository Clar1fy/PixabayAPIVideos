package com.example.android3fetchvideosfrompixabay.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3fetchvideosfrompixabay.databinding.TranslationHolderBinding;
import com.example.android3fetchvideosfrompixabay.network.network_model.responses.rapid.RapidHits;

import java.util.ArrayList;
import java.util.List;

public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.TranslationHolder> {
    List<RapidHits> list = new ArrayList<>();

    @NonNull
    @Override
    public TranslationAdapter.TranslationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TranslationHolder(TranslationHolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TranslationAdapter.TranslationHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<RapidHits> list) {
        this.list = list;
    }

    public class TranslationHolder extends RecyclerView.ViewHolder {
        private TranslationHolderBinding binding;

        public TranslationHolder(@NonNull TranslationHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(RapidHits rapidHits) {
            binding.tvTranslation.setText(rapidHits.getId());
        }
    }
}
