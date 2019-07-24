package id.shobrun.moviecatalogue.presenter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.adapter.TVShowAdapter;
import id.shobrun.moviecatalogue.component.adapter.TVShowViewHolder;
import id.shobrun.moviecatalogue.data.Movie;
import id.shobrun.moviecatalogue.model.MovieModel;

public class TVShowRecyclerPresenter {
    private MovieModel movieModel;
    private ArrayList<Movie> movies = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Context ctx;
    private int[] notifications = {R.drawable.ic_notifications_white_24dp,R.drawable.ic_notifications_active_white_24dp};
    public TVShowRecyclerPresenter(RecyclerView mRecyclerView, Context ctx) {
        this.ctx = ctx;
        this.movieModel = new MovieModel(ctx);
        this.movies.addAll(movieModel.getAllMovies());
        this.mRecyclerView = mRecyclerView;
    }

    public TVShowRecyclerPresenter(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    public void onBindItemViewHolder(TVShowViewHolder view, int position){
        Movie movie = movies.get(position);
        view.setPoster(movie.getPoster());
        view.setNotification(notifications[0]);
    }

    public int getMovieCount(){
        return movies.size();
    }

    public void loadRecyclerView(MovieModel model){
        movieModel = model;
        movies = model.getAllMovies();
        TVShowAdapter tvShowAdapter = new TVShowAdapter(this);
        mRecyclerView.setAdapter(tvShowAdapter);
    }

}
