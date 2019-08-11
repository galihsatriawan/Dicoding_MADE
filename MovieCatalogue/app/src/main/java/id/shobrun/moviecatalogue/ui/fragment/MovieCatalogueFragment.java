package id.shobrun.moviecatalogue.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.data.Movie;
import id.shobrun.moviecatalogue.contracts.MovieCatalogueContract;
import id.shobrun.moviecatalogue.models.MovieModel;
import id.shobrun.moviecatalogue.presenters.MovieCataloguePresenter;
import id.shobrun.moviecatalogue.presenters.MovieRecyclerPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCatalogueFragment extends Fragment implements MovieCatalogueContract.View {
    RecyclerView mRecyclerView;
    MovieCataloguePresenter mMovieCataloguePresenter;
    MovieRecyclerPresenter mMovieRecyclerPresenter;
    private static MovieCatalogueFragment instance;
    public static MovieCatalogueFragment getMovieCatalogueInstance(){
        if(instance == null){
            instance = new MovieCatalogueFragment();
        }
        return instance;
    }
    public MovieCatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_catalogue, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        mMovieCataloguePresenter.loadMovieCatalogue();


    }
    public void initPresenter(){
        mMovieCataloguePresenter = new MovieCataloguePresenter(this,getContext());
        //Create Recycler Presenter
        mMovieRecyclerPresenter = new MovieRecyclerPresenter(mRecyclerView);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void initUI() {
        mRecyclerView = this.getView().findViewById(R.id.recycler_movie_catalogue);
    }

    @Override
    public void showListMovieCatalogue(ArrayList<Movie> movies) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMovieRecyclerPresenter.loadRecyclerView(movies);
    }

    @Override
    public void showDetailMovie(Movie movie) {

    }

    @Override
    public void onResponseFailure() {

    }
}
