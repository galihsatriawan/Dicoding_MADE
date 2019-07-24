package id.shobrun.moviecatalogue.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.ui.DetailMovieActivity;
import id.shobrun.moviecatalogue.component.adapter.MovieAdapter;
import id.shobrun.moviecatalogue.component.adapter.MovieViewHolder;
import id.shobrun.moviecatalogue.component.common.OnItemClickListener;
import id.shobrun.moviecatalogue.component.common.OnViewClickListener;
import id.shobrun.moviecatalogue.data.Movie;
import id.shobrun.moviecatalogue.model.MovieModel;

public class MovieRecyclerPresenter {
    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieModel mMovieModel;
    private RecyclerView mMovieCatalogueView;
    private Context ctx;
    public MovieRecyclerPresenter(RecyclerView mMovieCatalogueView, Context ctx) {
        this.mMovieCatalogueView = mMovieCatalogueView;
        mMovieModel = new MovieModel(ctx);
        this.ctx = ctx;
        movies.addAll(mMovieModel.getAllMovies());
    }

    public MovieRecyclerPresenter(RecyclerView mMovieCatalogueView) {
        this.mMovieCatalogueView = mMovieCatalogueView;
    }

    public void onBindItemViewHolder(final MovieViewHolder viewHolder, int position){
        final Movie movie = movies.get(position);
        viewHolder.setTitle(movie.getName());
        viewHolder.setExcerpt(movie.getDescription());
        viewHolder.setRating(movie.getRating());
        viewHolder.setPoster(movie.getPoster());
        viewHolder.setOnViewClickListener(new OnViewClickListener() {
            @Override
            public void onViewClicked(View view) {

            }

            @Override
            public void onViewClicked(View v1, View v2) {
                if(v1.getVisibility()==View.VISIBLE){
                    viewHolder.showView(v2);
                    viewHolder.hideView(v1);
                    Toast.makeText(viewHolder.itemView.getContext(),movie.getName()+" has removed",Toast.LENGTH_SHORT).show();
                }else{
                    viewHolder.showView(v1);
                    viewHolder.hideView(v2);
                    Toast.makeText(viewHolder.itemView.getContext(),movie.getName()+" has added",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public int  getMoviesCount(){
        return movies.size();
    }
    public void loadRecyclerView(MovieModel model){
        mMovieModel = model;
        movies = mMovieModel.getAllMovies();
        MovieAdapter movieAdapter = new MovieAdapter(this);
        movieAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(View v, int position) {
                Intent detail = new Intent(v.getContext(), DetailMovieActivity.class);
                detail.putExtra(DetailMovieActivity.EXTRA_MOVIE,movies.get(position));
                v.getContext().startActivity(detail);
            }
        });
        mMovieCatalogueView.setAdapter(movieAdapter);
    }


}
