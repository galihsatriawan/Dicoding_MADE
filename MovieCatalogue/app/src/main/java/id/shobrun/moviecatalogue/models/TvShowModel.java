package id.shobrun.moviecatalogue.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.data.TvShow;
import id.shobrun.moviecatalogue.component.response.TvShowListResponse;
import id.shobrun.moviecatalogue.contracts.TvShowContract;
import id.shobrun.moviecatalogue.network.ApiClient;
import id.shobrun.moviecatalogue.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowModel implements TvShowContract.Model {
    private Context context;
    private ArrayList<TvShow> tvShows;
    private ArrayList<TvShow> tvShowsPopular;
    private ArrayList<TvShow> tvShowsTrending;
    public TvShowModel(Context ctx){
        this.context = ctx;
    }
    @Override
    public ArrayList<TvShow> getAllTvShowPopular(final OnFinishedListener onFinishedListener) {
        try {

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            HashMap<String,String> options = new HashMap<>();
            options.put("api_key",this.context.getString(R.string.api_key));
            options.put("language","en-US");

            Call<TvShowListResponse> response = apiService.getTvMovie(options);
            response.enqueue(new Callback<TvShowListResponse>() {
                @Override
                public void onResponse(Call<TvShowListResponse> call, Response<TvShowListResponse> response) {
                    if(response.isSuccessful()){
                        onFinishedListener.onSuccess(response);
                        tvShowsPopular = response.body().getResults();
                    }else {
                        onFinishedListener.onError(response);
                    }
                }

                @Override
                public void onFailure(Call<TvShowListResponse> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return tvShowsPopular;
    }

    @Override
    public ArrayList<TvShow> getAllTvShowTrending(final OnFinishedListener onFinishedListener) {
        try{
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            HashMap<String,String> options = new HashMap<>();
            options.put("api_key",this.context.getString(R.string.api_key));
            options.put("language","en-US");

            Call<TvShowListResponse> response = apiService.getTvMovie(options);
            response.enqueue(new Callback<TvShowListResponse>() {
                @Override
                public void onResponse(Call<TvShowListResponse> call, Response<TvShowListResponse> response) {
                    if (response.isSuccessful()){
                        onFinishedListener.onSuccess(response);
                        tvShowsTrending= response.body().getResults();
                    }else {
                        onFinishedListener.onError(response);
                    }
                }

                @Override
                public void onFailure(Call<TvShowListResponse> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return tvShowsTrending;
    }


    @Override
    public int getTvShow(int id) {
        int position = -1;
        int index = 0;
        for (TvShow item: tvShows) {
            if(item.getId()==id) {
                position = index;
                break;
            }
            index++;
        }
        return position;
    }

    @Override
    public int getTvShowCount() {
        return tvShows.size();
    }
}
