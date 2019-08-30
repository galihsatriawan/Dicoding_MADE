package id.shobrun.moviecatalogue.models;


import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

import id.shobrun.moviecatalogue.BuildConfig;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.api.response.MovieListResponse;
import id.shobrun.moviecatalogue.contracts.MovieCatalogueContract;
import id.shobrun.moviecatalogue.api.ApiClient;
import id.shobrun.moviecatalogue.api.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieModel implements MovieCatalogueContract.Model {
    private ArrayList<Movie> movies;
    private Context ctx;
    public MovieModel(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public ArrayList<Movie> getAllMovies(final OnFinishedListener onFinishedListener) {
        Log.e(getClass().getSimpleName(), "getAllMovies: get" );
        try {
        /*
            Get Data With Retrofit
         */

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        /**
         * Query Request
         */
        HashMap<String, String> options = new HashMap<>();
        options.put("api_key", BuildConfig.ApiKey);
        options.put("language","en-US");
        Call<MovieListResponse> response = apiService.getMovie(options);
        response.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if(response.isSuccessful()){
                    onFinishedListener.onFinished(response);
                    movies = response.body().getResults();
                }else {
                    onFinishedListener.onError(response);
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
        }catch (Exception e){
            e.printStackTrace();
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
