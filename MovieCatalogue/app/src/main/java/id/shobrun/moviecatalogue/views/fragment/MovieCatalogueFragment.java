package id.shobrun.moviecatalogue.views.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.contracts.MovieCatalogueContract;
import id.shobrun.moviecatalogue.presenters.MovieCataloguePresenter;
import id.shobrun.moviecatalogue.presenters.MovieRecyclerPresenter;
import id.shobrun.moviecatalogue.viewmodels.MovieCatalogueViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCatalogueFragment extends Fragment implements MovieCatalogueContract.View {
    private final String TAG = this.getClass().getSimpleName();
    RecyclerView mRecyclerView;
    MovieCataloguePresenter mMovieCataloguePresenter;
    MovieRecyclerPresenter mMovieRecyclerPresenter;
    ProgressBar progressBar;

    MovieCatalogueViewModel viewModel;
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
        initViewModel();
    }
    public void initPresenter(){
        mMovieCataloguePresenter = new MovieCataloguePresenter(this,getContext());
        mMovieRecyclerPresenter = new MovieRecyclerPresenter(mRecyclerView);
        Log.e(TAG, "initPresenter: " );
    }
    public void initViewModel(){
        viewModel = ViewModelProviders.of(this).get(MovieCatalogueViewModel.class);
        viewModel.getMovies().observe(this,getMovies);
        mMovieCataloguePresenter.loadMovieCatalogue(viewModel);
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void initUI() {
        mRecyclerView = this.getView().findViewById(R.id.recycler_movie_catalogue);
        progressBar = this.getView().findViewById(R.id.progressBar);
    }

    @Override
    public void showMessage(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showListMovieCatalogue(ArrayList<Movie> movies) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMovieRecyclerPresenter.loadRecyclerView(movies);
    }

    private Observer<ArrayList<Movie>> getMovies = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if(movies != null){
                mMovieCataloguePresenter.onRefresh(movies);
            }

        }
    };
}
