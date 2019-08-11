package id.shobrun.moviecatalogue.presenters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.contracts.MovieCatalogueRecyclerContract;
import id.shobrun.moviecatalogue.ui.DetailMovieActivity;
import id.shobrun.moviecatalogue.component.adapter.MovieAdapter;
import id.shobrun.moviecatalogue.component.adapter.MovieViewHolder;
import id.shobrun.moviecatalogue.component.common.OnItemClickListener;
import id.shobrun.moviecatalogue.component.common.OnViewClickListener;
import id.shobrun.moviecatalogue.component.data.Movie;
import id.shobrun.moviecatalogue.models.MovieModel;

public class MovieRecyclerPresenter implements MovieCatalogueRecyclerContract.RecyclerPresenter {
    private ArrayList<Movie> movies = new ArrayList<>();
    private RecyclerView mMovieCatalogueView;

    public MovieRecyclerPresenter(RecyclerView mMovieCatalogueView) {
        this.mMovieCatalogueView = mMovieCatalogueView;
    }
    @Override
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
    @Override
    public int  getMoviesCount(){
        return movies.size();
    }


    @Override
    public void loadRecyclerView(final ArrayList<Movie> movies){
        this.movies = movies;
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
    @Override
    public void updateRecycler() {
        mMovieCatalogueView.getAdapter().notifyDataSetChanged();
    }


}
