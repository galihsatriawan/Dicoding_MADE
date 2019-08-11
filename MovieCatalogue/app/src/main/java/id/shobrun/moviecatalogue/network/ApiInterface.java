package id.shobrun.moviecatalogue.network;

import id.shobrun.moviecatalogue.component.response.MovieListResponse;
import id.shobrun.moviecatalogue.component.response.TvMovieListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("discover/movie")
    Call<MovieListResponse> getMovie(@Query("api_key") String apiKey,@Query("language") String language);

    @GET("discover/tv")
    Call<TvMovieListResponse> getTvMovie(@Query("api_key") String apiKey, @Query("language") String language);

}
