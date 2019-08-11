package id.shobrun.moviecatalogue.views;

import android.view.View;

public interface MovieItemView {
    void setTitle(String title);
    void setExcerpt(String excerpt);
    void setRating(double rating);
    void setPoster(int poster);
    void showView(View v);
    void hideView(View v);

}
