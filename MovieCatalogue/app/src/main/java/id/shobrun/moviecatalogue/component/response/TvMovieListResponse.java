package id.shobrun.moviecatalogue.component.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.component.data.Movie;
import id.shobrun.moviecatalogue.component.data.TvMovie;

public class TvMovieListResponse {
    int page;
    @SerializedName("total_results")
    int totalResults ;
    @SerializedName("total_pages")
    int totalPages;
    ArrayList<TvMovie> results;

    public TvMovieListResponse(int page, int totalResults, int totalPages, ArrayList<TvMovie> results) {
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

    public ArrayList<TvMovie> getResults() {
        return results;
    }

    public void setResults(ArrayList<TvMovie> results) {
        this.results = results;
    }
}
