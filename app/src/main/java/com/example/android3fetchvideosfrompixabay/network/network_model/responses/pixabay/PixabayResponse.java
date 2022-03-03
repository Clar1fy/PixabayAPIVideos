package com.example.android3fetchvideosfrompixabay.network.network_model.responses.pixabay;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PixabayResponse {
    @SerializedName("total")
    private Integer total;
    @SerializedName("totalHits")
    private Integer totalHits;
    @SerializedName("hits")
    private List<Hits> hits = null;

    public List<Hits> getHits() {
        return hits;
    }


}
