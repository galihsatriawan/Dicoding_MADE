package id.shobrun.moviecatalogue.contracts;

import id.shobrun.moviecatalogue.component.data.Movie;
import id.shobrun.moviecatalogue.models.MovieModel;

public interface DetailMovieContract {

    interface Model{

    }
    interface View extends RootView {
        void initUI();
        void showDetailMovie(Movie movie);
    }
    interface Presenter{

    }

}
