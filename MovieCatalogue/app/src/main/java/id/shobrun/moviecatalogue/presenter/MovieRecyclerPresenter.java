package id.shobrun.moviecatalogue.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.component.MovieAdapter;
import id.shobrun.moviecatalogue.component.MovieViewHolder;
import id.shobrun.moviecatalogue.data.Movie;
import id.shobrun.moviecatalogue.model.MovieModel;
import id.shobrun.moviecatalogue.view.MovieCatalogueView;

public class MovieRecyclerPresenter {
    private ArrayList<Movie> movies;
    private MovieModel mMovieModel;
    private RecyclerView mMovieCatalogueView;
    public MovieRecyclerPresenter(RecyclerView mMovieCatalogueView, MovieModel mMovieModel) {
        this.mMovieCatalogueView = mMovieCatalogueView;
        this.mMovieModel =mMovieModel;
        movies.addAll(mMovieModel.getAllMovies());
    }
    public MovieRecyclerPresenter(RecyclerView mMovieCatalogueView, Context ctx) {
        this.mMovieCatalogueView = mMovieCatalogueView;
        mMovieModel = new MovieModel(ctx);
        movies.addAll(mMovieModel.getAllMovies());
    }

    public MovieRecyclerPresenter(RecyclerView mMovieCatalogueView) {
        this.mMovieCatalogueView = mMovieCatalogueView;
    }

    public void onBindItemViewHolder(MovieViewHolder viewHolder, int position){
        Movie movie = movies.get(position);
        viewHolder.setTitle(movie.getName());
        viewHolder.setExcerpt(movie.getDescription());
        viewHolder.setRating(movie.getRating());
        viewHolder.setPoster(movie.getPoster());
    }

    public int  getMoviesCount(){
        return movies.size();
    }
    public void loadRecyclerView(MovieModel model){
        mMovieModel = model;
        movies = mMovieModel.getAllMovies();
        mMovieCatalogueView.setAdapter(new MovieAdapter(this));
    }
}
