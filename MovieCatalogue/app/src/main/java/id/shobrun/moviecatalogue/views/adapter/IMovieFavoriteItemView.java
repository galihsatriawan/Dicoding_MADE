package id.shobrun.moviecatalogue.views.adapter;

import android.view.View;

import id.shobrun.moviecatalogue.models.data.Movie;

public interface IMovieFavoriteItemView {
    void setTitle(String title);
    void setExcerpt(String excerpt);
    void setRating(double rating);
    void setPoster(String poster);
}
