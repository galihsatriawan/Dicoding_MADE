package id.shobrun.moviecatalogue.views.adapter;

import android.view.View;

public interface IMovieItemView {
    void setTitle(String title);
    void setExcerpt(String excerpt);
    void setRating(double rating);
    void setPoster(String poster);
    void showView(View v);
    void hideView(View v);

}
