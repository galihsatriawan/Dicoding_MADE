package id.shobrun.moviecatalogue.contracts;

import android.view.View;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.views.adapter.MovieViewHolder;
import id.shobrun.moviecatalogue.models.data.Movie;

public interface MovieCatalogueRecyclerContract {
    interface MovieItemView {
        void setTitle(String title);
        void setExcerpt(String excerpt);
        void setRating(double rating);
        void setPoster(String poster);
        void showView(View v);
        void hideView(View v);

    }
    interface RecyclerPresenter{
        void loadRecyclerView(ArrayList<Movie> movies);
        void onBindItemViewHolder(final MovieViewHolder viewHolder, int position);
        int  getMoviesCount();
        void setMovies(ArrayList<Movie> movies);
    }
}
