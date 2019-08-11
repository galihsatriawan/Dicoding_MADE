package id.shobrun.moviecatalogue.network;

import java.util.HashMap;

import id.shobrun.moviecatalogue.component.response.MovieListResponse;
import id.shobrun.moviecatalogue.component.response.TvMovieListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("discover/movie")
    Call<MovieListResponse> getMovie(@QueryMap HashMap<String, String> queries);

    @GET("discover/tv")
    Call<TvMovieListResponse> getTvMovie(@QueryMap HashMap<String, String> queries);

}
