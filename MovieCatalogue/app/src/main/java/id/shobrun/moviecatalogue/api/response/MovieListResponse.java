package id.shobrun.moviecatalogue.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.models.data.Movie;

public class MovieListResponse {
    private int page;
    @SerializedName("total_results")
    private
    int totalResults ;
    @SerializedName("total_pages")
    private
    int totalPages;
    private ArrayList<Movie> results;

    public MovieListResponse(int page, int totalResults, int totalPages, ArrayList<Movie> results) {
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

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
