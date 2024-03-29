package id.shobrun.consumer.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.shobrun.consumer.models.data.TvShow;

public class TvShowListResponse {
    private int page;
    @SerializedName("total_results")
    private
    int totalResults ;
    @SerializedName("total_pages")
    private
    int totalPages;
    private ArrayList<TvShow> results;

    public TvShowListResponse(int page, int totalResults, int totalPages, ArrayList<TvShow> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<TvShow> getResults() {
        return results;
    }

    public void setResults(ArrayList<TvShow> results) {
        this.results = results;
    }
}
