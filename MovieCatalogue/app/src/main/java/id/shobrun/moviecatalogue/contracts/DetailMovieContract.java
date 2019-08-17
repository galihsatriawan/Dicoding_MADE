package id.shobrun.moviecatalogue.contracts;

import id.shobrun.moviecatalogue.component.data.Movie;

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
