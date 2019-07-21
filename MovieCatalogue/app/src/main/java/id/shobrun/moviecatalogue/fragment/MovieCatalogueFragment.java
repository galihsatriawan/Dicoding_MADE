package id.shobrun.moviecatalogue.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.MovieAdapter;
import id.shobrun.moviecatalogue.data.Movie;
import id.shobrun.moviecatalogue.model.MovieModel;
import id.shobrun.moviecatalogue.presenter.MovieCataloguePresenter;
import id.shobrun.moviecatalogue.presenter.MovieRecyclerPresenter;
import id.shobrun.moviecatalogue.view.MovieCatalogueView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCatalogueFragment extends Fragment implements MovieCatalogueView {
    RecyclerView mRecyclerView;
    MovieCataloguePresenter mMovieCataloguePresenter;
    MovieRecyclerPresenter mMovieRecylerPresenter;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_catalogue, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_movie_catalogue);
        initPresenter();
        mMovieCataloguePresenter.loadMovieCatalogue();


    }
    public void initPresenter(){
        mMovieCataloguePresenter = new MovieCataloguePresenter(this,getContext());
        //Create Recycler Presenter
        mMovieRecylerPresenter = new MovieRecyclerPresenter(mRecyclerView);
    }

    @Override
    public void showListMovieCatalogue(MovieModel model) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMovieRecylerPresenter.loadRecyclerView(model);
    }
}
