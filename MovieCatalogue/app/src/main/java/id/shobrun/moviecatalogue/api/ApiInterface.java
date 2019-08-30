package id.shobrun.moviecatalogue.api;

import java.util.HashMap;

import id.shobrun.moviecatalogue.api.response.MovieListResponse;
import id.shobrun.moviecatalogue.api.response.TvShowListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("discover/movie")
    Call<MovieListResponse> getMovie(@QueryMap HashMap<String, String> queries);

    @GET("discover/tv")
    Call<TvShowListResponse> getTvMovie(@QueryMap HashMap<String, String> queries);

}