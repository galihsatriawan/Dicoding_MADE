package id.shobrun.moviecatalogue.repositories.remote;

import android.content.Context;

import java.util.HashMap;
import java.util.List;

import id.shobrun.moviecatalogue.BuildConfig;
import id.shobrun.moviecatalogue.api.ApiClient;
import id.shobrun.moviecatalogue.api.ApiInterface;
import id.shobrun.moviecatalogue.api.response.MovieListResponse;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.repositories.IMoviesDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRemoteData implements IMoviesDataSource.ApiSource {
    private ApiInterface apiService ;
    private List<Movie> movies ;

    public MovieRemoteData(Context context){
        Context context1 = context;
        this.apiService = ApiClient.getClient().create(ApiInterface.class);
    }
    @Override
    public List<Movie> getMoviesData(final OnFinishedListener listener) {
        try{
            HashMap<String,String> options = new HashMap<>();
            options.put("api_key", BuildConfig.ApiKey);
            options.put("language","en-US");
            Call<MovieListResponse> response = apiService.getMovie(options);
            response.enqueue(new Callback<MovieListResponse>() {
                @Override
                public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                    if(response.isSuccessful()){
                        listener.onFinished(response);
                        movies = response.body().getResults();
                    }else{
                        listener.onError(response);
                    }
                }

                @Override
                public void onFailure(Call<MovieListResponse> call, Throwable t) {
                    listener.onFailure(t);
                }
            });
        }catch (Throwable t){
            listener.onFailure(t);
        }

        return movies;
    }

    @Override
    public List<Movie> getSearchMoviesData(String str, final OnFinishedListener listener) {
        try {
            HashMap<String, String> options = new HashMap<>();
            options.put("api_key",BuildConfig.ApiKey);
            options.put("language","en-US");
            options.put("query",str);
            Call<MovieListResponse> response = apiService.getSearchMovie(options);
            response.enqueue(new Callback<MovieListResponse>() {
                @Override
                public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                    if(response.isSuccessful()){
                        listener.onFinished(response);
                    }else{
                        listener.onError(response);
                    }
                }

                @Override
                public void onFailure(Call<MovieListResponse> call, Throwable t) {
                    listener.onFailure(t);
                }
            });
        }catch (Throwable t){
            listener.onFailure(t);
        }
        return movies;
    }

    @Override
    public Movie getMovie(int position) {
        Movie movie = null;
        try{
            movie =movies.get(position);
        }catch (Exception e){
            e.printStackTrace();
        }

        return movie;
    }

    @Override
    public int getMovieById(int id) {
        int position =-1 ;
        int index = 0;
        for(Movie movie : movies){
            if(movie.getId()==id) {
                position = index;
                return position;
            }
            index++;
        }
        return position;
    }

    @Override
    public int getMovieCount() {
        return movies.size();
    }

}
