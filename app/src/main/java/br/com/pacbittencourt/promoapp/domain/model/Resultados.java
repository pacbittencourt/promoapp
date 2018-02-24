package br.com.pacbittencourt.promoapp.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Resultados {

    @SerializedName("results")
    private List<ResultsItem> results;

    public void setResults(List<ResultsItem> results) {
        this.results = results;
    }

    public List<ResultsItem> getResults() {
        return results;
    }
}