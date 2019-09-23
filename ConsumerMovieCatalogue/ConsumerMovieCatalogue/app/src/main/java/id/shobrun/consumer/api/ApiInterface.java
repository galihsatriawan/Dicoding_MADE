package id.shobrun.consumer.api;

import java.util.HashMap;

import id.shobrun.consumer.api.response.MovieListResponse;
import id.shobrun.consumer.api.response.TvShowListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("discover/movie")
    Call<MovieListResponse> getMovie(@QueryMap HashMap<String, String> queries);

    @GET("discover/tv")
    Call<TvShowListResponse> getTvShow(@QueryMap HashMap<String, String> queries);

    @GET("search/movie")
    Call<MovieListResponse> getSearchMovie(@QueryMap HashMap<String, String> queries);

    @GET("search/tv")
    Call<TvShowListResponse> getSearchTv(@QueryMap HashMap<String, String> queries);
}
